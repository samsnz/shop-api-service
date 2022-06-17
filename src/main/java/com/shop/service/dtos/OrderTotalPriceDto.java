package com.shop.service.dtos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.shop.service.models.Cargo;
import com.shop.service.models.Client;
import com.shop.service.models.Order;
import com.shop.service.models.OrderDrink;
import com.shop.service.models.enums.EOrderStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderTotalPriceDto {

    private long id;

    private String details;

    private Date date;

    private EOrderStatus status;

    private Client client;

    private Cargo cargo;

    private List<OrderDrink> orderDrinks;

    private Double totalPrice;

}
