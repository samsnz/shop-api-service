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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/cargos", produces = "application/json")
@Tag(name = "CARGO", description = "APIs to perform operations on cargos")
public class CargoController {

    @Autowired
    private CargoService cargoService;

    @GetMapping
    @Operation(summary = "Cargo 1: Get All Cargos", description = "Get a list of all cargo companies", tags = {
            "getAllCargos" })
    public List<Cargo> getAllCargos() {
        return cargoService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Cargo 2: Get Specific Cargo by ID", description = "Get a specific cargo company by ID", tags = {
            "getCargoById" })
    public ResponseEntity<Cargo> getCargoById(@PathVariable("id") Long id) {
        Cargo cargo = cargoService.findCargoById(id);

        if (cargo != null) {
            return ResponseEntity.ok().body(cargo);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @GetMapping("/{cargoCode}/drinks")
    @Operation(summary = "Cargo 3: Get All Drinks of a Specific Cargo in Date Range", description = "For a specific cargo company, get a list of drinks transported by date range", tags = {
            "findAllTransportedDrinksByCargoWithinDates" })
    public Set<CargoDrinkDto> findAllTransportedDrinksByCargoWithinDates(
            @RequestParam(name = "startDate") String startDate, @RequestParam(name = "endDate") String endDate,
            @PathVariable("cargoCode") String cargoCode) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Date startDateParsed = sdf.parse(startDate);

        Date endDateParsed = sdf.parse(endDate);

        return cargoService.findAllTransportedDrinksByCargoWithinDates(startDateParsed, endDateParsed, cargoCode);

    }
}
