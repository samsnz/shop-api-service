package com.shop.service.dtos;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateNewOrderRequestDto {

    private String orderDetails;

    private String cargoCode;

    private Long clientId;

    private List<OrderDrinkDto> orderDrinksList = new ArrayList<>();

}
