package com.shop.service.services;

import java.util.List;

import com.shop.service.dtos.CreateNewOrderRequestDto;
import com.shop.service.models.Order;

public interface OrderService {

    Order findById(Long id);

    List<Order> findAll();

    Order save(CreateNewOrderRequestDto createNewOrderRequestDto);

}
