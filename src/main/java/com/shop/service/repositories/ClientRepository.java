package com.shop.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shop.service.models.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}
