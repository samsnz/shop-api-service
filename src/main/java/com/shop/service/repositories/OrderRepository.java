package com.shop.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shop.service.models.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
