package com.shop.service.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shop.service.dtos.CargoDrinkDto;
import com.shop.service.models.Cargo;
import com.shop.service.repositories.CargoRepository;
import com.shop.service.services.CargoService;

@RestController
@RequestMapping(value = "/cargos", produces = "application/json")
public class CargoController {

    @Autowired
    private CargoService cargoService;

    @GetMapping
    public List<Cargo> getAllClients() {
        return cargoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cargo> getClientById(@PathVariable("id") Long id) {
        Cargo cargo = cargoService.findCargoById(id);

        if (cargo != null) {
            return ResponseEntity.ok().body(cargo);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @GetMapping("/{cargoCode}/drinks")
    public Set<CargoDrinkDto> findAllTransportedDrinksByCargoWithinDates(
            @RequestParam(name = "startDate") String startDate, @RequestParam(name = "endDate") String endDate,
            @PathVariable("cargoCode") String cargoCode) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date startDateParsed = sdf.parse(startDate);

        Date endDateParsed = sdf.parse(endDate);

        return cargoService.findAllTransportedDrinksByCargoWithinDates(startDateParsed, endDateParsed, cargoCode);

    }
}
