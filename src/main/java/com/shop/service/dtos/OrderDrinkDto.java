package com.shop.service.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderDrinkDto {

    private String drinkCode;

    private Integer quantityOrdered;

}
