package com.shop.service.services;

import java.util.List;

import com.shop.service.dtos.CreateNewOrderRequestDto;
import com.shop.service.dtos.OrderTotalPriceDto;
import com.shop.service.dtos.views.OrderTotalPriceView;
import com.shop.service.models.Order;
import com.shop.service.models.Receipt;

public interface OrderService {

    Order findById(Long id);

    List<Order> findAll();

    Order save(CreateNewOrderRequestDto createNewOrderRequestDto);

    List<Order> findTop5RecentRowsForDifferentClients();

    List<OrderTotalPriceDto> findTop10PaidOrdersWithClientAndTransporterDetails();

    Receipt completeOrder(Long orderId);

}
