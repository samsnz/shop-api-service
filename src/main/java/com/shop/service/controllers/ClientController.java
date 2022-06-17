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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/clients", produces = "application/json")
@Tag(name = "CLIENT", description = "APIs to perform operations on clients")
public class ClientController {

    @Autowired
    private CargoService cargoService;

    @Autowired
    private ClientService clientService;

    @GetMapping
    @Operation(summary = "Client 1: Get All Clients", description = "Get a list of all clients", tags = {
            "getAllClients" })
    public List<Client> getAllClients() {
        return clientService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Client 2: Get Specific Client by ID", description = "Get a specific client by ID", tags = {
            "getClientById" })
    public ResponseEntity<Client> getClientById(@PathVariable("id") Long id) {
        Client client = clientService.findClientById(id);

        if (client != null) {
            return ResponseEntity.ok().body(client);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @GetMapping("/{clientId}/cargos")
    @Operation(summary = "Client 3: Get Top Nearest Cargo to Client", description = "For a specific retailer client/hotel, get a list of the 3 closest cargo companies", tags = {
            "findTopClosestCargo" })
    public List<NearestCargoView> findTopClosestCargo(@PathVariable("clientId") Long clientId,
            @RequestParam(name = "limit") Integer limit) {
        return cargoService.findTopClosestCargo(clientId, limit);
    }

}
