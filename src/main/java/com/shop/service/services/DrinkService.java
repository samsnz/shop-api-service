package com.shop.service.services;

import java.util.List;

import com.shop.service.dtos.views.TopConsumedDrinkView;
import com.shop.service.models.Drink;

public interface DrinkService {

    List<Drink> findAll();

    List<Drink> findAllAvailable();

    Drink findDrinkById(Long id);

    List<TopConsumedDrinkView> getTopConsumedDrinksWithQuantity(Integer limit);

}
