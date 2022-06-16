package com.shop.service.services;

import com.shop.service.dtos.CreateNewOrderRequestDto;
import com.shop.service.models.Order;

public interface OrderService {

    Order save(CreateNewOrderRequestDto createNewOrderRequestDto);

}
