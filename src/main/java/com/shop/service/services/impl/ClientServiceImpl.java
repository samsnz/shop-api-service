package com.shop.service.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.service.models.Client;
import com.shop.service.repositories.ClientRepository;
import com.shop.service.services.ClientService;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client findClientById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

}
