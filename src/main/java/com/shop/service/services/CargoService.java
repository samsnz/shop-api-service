package com.shop.service.services;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.shop.service.dtos.CargoDrinkDto;
import com.shop.service.dtos.views.NearestCargoView;
import com.shop.service.models.Cargo;

public interface CargoService {

    List<Cargo> findAll();

    Cargo findCargoById(Long id);

    NearestCargoView findNearestCargoWithin(Long clientId, Double distance);

    List<NearestCargoView> findTopClosestCargo(Long clientId, Integer limit);

    Set<CargoDrinkDto> findAllTransportedDrinksByCargoWithinDates(Date startDate, Date endDate, String cargoCode);

}
