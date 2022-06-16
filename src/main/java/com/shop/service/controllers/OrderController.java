package com.shop.service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.service.dtos.CreateNewOrderRequestDto;
import com.shop.service.models.Order;
import com.shop.service.services.OrderService;

@RestController
@RequestMapping(value = "/orders", produces = "application/json")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.findAll();
    }

    @PostMapping
    @Transactional(rollbackFor = { RuntimeException.class })
    public ResponseEntity<Order> createNewOrder(@RequestBody CreateNewOrderRequestDto createNewOrderRequestDto) {

        Order order = orderService.save(createNewOrderRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(order);

    }

}
