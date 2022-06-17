package com.shop.service.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.service.dtos.views.TopConsumedDrinkView;
import com.shop.service.models.Drink;
import com.shop.service.repositories.DrinkRepository;
import com.shop.service.services.DrinkService;

@Service
public class DrinkServiceImpl implements DrinkService {

    @Autowired
    private DrinkRepository drinkRepository;

    @Override
    public List<Drink> findAll() {
        return drinkRepository.findAll();
    }

    @Override
    public List<Drink> findAllAvailable() {
        return drinkRepository.findByQuantityGreaterThan(0);
    }

    @Override
    public Drink findDrinkById(Long id) {
        return drinkRepository.findById(id).orElse(null);
    }

    @Override
    public List<TopConsumedDrinkView> getTopConsumedDrinksWithQuantity(Integer limit) {
        return drinkRepository.getTopConsumedDrinksWithQuantity(limit);
    }

}
