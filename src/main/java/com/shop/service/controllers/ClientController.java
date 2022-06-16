package com.shop.service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shop.service.dtos.views.NearestCargoView;
import com.shop.service.models.Client;
import com.shop.service.services.CargoService;
import com.shop.service.services.ClientService;

@RestController
@RequestMapping(value = "/clients", produces = "application/json")
public class ClientController {

    @Autowired
    private CargoService cargoService;

    @Autowired
    private ClientService clientService;

    @GetMapping
    public List<Client> getAllClients() {
        return clientService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable("id") Long id) {
        Client client = clientService.findClientById(id);

        if (client != null) {
            return ResponseEntity.ok().body(client);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @GetMapping("/{clientId}/cargos")
    public List<NearestCargoView> findTopClosestCargo(@PathVariable("clientId") Long clientId,
            @RequestParam(name = "limit") Integer limit) {
        return cargoService.findTopClosestCargo(clientId, limit);
    }

}
