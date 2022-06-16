package com.shop.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shop.service.models.OrderDrink;

@Repository
public interface OrderDrinkRepository extends JpaRepository<OrderDrink, Long> {

}
