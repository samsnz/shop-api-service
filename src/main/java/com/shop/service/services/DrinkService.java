package com.shop.service.services;

import java.util.List;

import com.shop.service.models.Drink;

public interface DrinkService {

    List<Drink> findAll();

    List<Drink> findAllAvailable();

    Drink findDrinkById(Long id);

}
