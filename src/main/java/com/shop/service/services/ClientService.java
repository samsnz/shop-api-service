package com.shop.service.services;

import java.util.List;

import com.shop.service.models.Client;

public interface ClientService {

    List<Client> findAll();

    Client findClientById(Long id);

}
