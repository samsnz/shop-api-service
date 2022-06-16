package com.shop.service.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.service.dtos.CargoDrinkDto;
import com.shop.service.dtos.views.NearestCargoView;
import com.shop.service.models.Cargo;
import com.shop.service.models.Client;
import com.shop.service.repositories.CargoRepository;
import com.shop.service.repositories.ClientRepository;
import com.shop.service.services.CargoService;

@Service
public class CargoServiceImpl implements CargoService {

    @Autowired
    private CargoRepository cargoRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public NearestCargoView findNearestCargoWithin(Long clientId, Double distance) {

        Client client = clientRepository.findById(clientId).orElse(null);

        if (client == null) {
            return null;
        }

        return cargoRepository.findNearestCargoWithin(client.getLongitude(), client.getLatitude(), distance);

    }

    @Override
    public List<NearestCargoView> findTopClosestCargo(Long clientId, Integer limit) {
        Client client = clientRepository.findById(clientId).orElse(null);

        if (client == null) {
            return new ArrayList<>();
        }

        return cargoRepository.findTopClosestCargo(client.getLongitude(), client.getLatitude(), limit);
    }

    @Override
    public List<Cargo> findAll() {
        return cargoRepository.findAll();
    }

    @Override
    public Cargo findCargoById(Long id) {
        return cargoRepository.findById(id).orElse(null);
    }

    @Override
    public Set<CargoDrinkDto> findAllTransportedDrinksByCargoWithinDates(Date startDate, Date endDate,
            String cargoCode) {
        return cargoRepository.findAllTransportedDrinksByCargoWithinDates(startDate, endDate, cargoCode);
    }

}
