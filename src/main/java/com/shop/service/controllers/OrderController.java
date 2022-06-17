package com.shop.service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.service.configurations.exceptions.CustomResponseException;
import com.shop.service.dtos.CreateNewOrderRequestDto;
import com.shop.service.dtos.OrderTotalPriceDto;
import com.shop.service.models.Order;
import com.shop.service.models.Receipt;
import com.shop.service.services.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/orders", produces = "application/json")
@Tag(name = "ORDER", description = "APIs to perform operations on orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.findAll();
    }

    @PostMapping
    @Transactional(rollbackFor = { CustomResponseException.class })
    @Operation(summary = "Order 1: Create New Order", description = "Create a new order request by assigning a list of drinks to a specific client", tags = {
            "createNewOrder" })
    public ResponseEntity<Order> createNewOrder(@RequestBody CreateNewOrderRequestDto createNewOrderRequestDto) {

        Order order = orderService.save(createNewOrderRequestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(order);

    }

    @GetMapping("/top-different")
    @Operation(summary = "Order 2: Get Top 5 Orders of Different Clients", description = "Choose top five orders that were requested by different clients", tags = {
            "findTop5RecentRowsForDifferentClients" })
    public List<Order> findTop5RecentRowsForDifferentClients() {
        return orderService.findTop5RecentRowsForDifferentClients();
    }

    @GetMapping("/top-paid")
    @Operation(summary = "Order 3: Get Top Paid Orders With Client and Transporter Details", description = "Get top 10 paid orders, their client details, and transporter details", tags = {
            "findTop10PaidOrdersWithClientAndTransporterDetails" })
    public List<OrderTotalPriceDto> findTop10PaidOrdersWithClientAndTransporterDetails() {
        return orderService.findTop10PaidOrdersWithClientAndTransporterDetails();
    }

    @PatchMapping("/{orderId}")
    @Operation(summary = "Order 4: Complete Order", description = "Complete Order", tags = {
            "completeOrder" })
    public ResponseEntity<Receipt> completeOrder(@PathVariable("orderId") Long orderId) {
        return ResponseEntity.ok().body(orderService.completeOrder(orderId));
    }

}
