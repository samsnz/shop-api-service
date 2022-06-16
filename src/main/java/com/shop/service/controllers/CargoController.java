package com.shop.service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.service.models.Cargo;
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
}
