package com.shop.service.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shop.service.dtos.views.NearestCargoView;
import com.shop.service.dtos.views.TopConsumedDrinkView;
import com.shop.service.models.Drink;
import com.shop.service.services.CargoService;
import com.shop.service.services.DrinkService;

@RestController
@RequestMapping(value = "/drinks", produces = "application/json")
public class DrinkController {

    @Autowired
    private DrinkService drinkService;

    @Autowired
    private CargoService cargoService;

    @GetMapping
    public List<Drink> getAllDrinks() {
        return drinkService.findAll();
    }

    @GetMapping("/near-me")
    public Map<String, Object> getAllAvailableDrinksAndNearestCargoWithin(
            @RequestParam(name = "clientId") Long clientId, @RequestParam(name = "distance") Double distance) {

        Map<String, Object> map = new HashMap<>();

        List<Drink> availableDrinks = drinkService.findAllAvailable();

        NearestCargoView nearestCargoDto = cargoService.findNearestCargoWithin(clientId, distance);

        map.put("availableDrinks", availableDrinks);
        map.put("nearestCargo", nearestCargoDto);

        return map;

    }

    @GetMapping("/{id}")
    public ResponseEntity<Drink> getDrinkById(@PathVariable("id") Long id) {
        Drink drink = drinkService.findDrinkById(id);

        if (drink != null) {
            return ResponseEntity.ok().body(drink);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @GetMapping("/most-consumed")
    public List<TopConsumedDrinkView> getTopConsumedDrinksWithQuantity(@RequestParam(name = "top") Integer top) {
        return drinkService.getTopConsumedDrinksWithQuantity(top);
    }

}
