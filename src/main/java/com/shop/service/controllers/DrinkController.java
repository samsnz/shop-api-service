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

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(value = "/drinks", produces = "application/json")
@Tag(name = "DRINKS", description = "APIs to perform operations on drinks")
public class DrinkController {

        @Autowired
        private DrinkService drinkService;

        @Autowired
        private CargoService cargoService;

        @GetMapping
        @Operation(summary = "Drinks 1: Get All Drinks", description = "Get a list of all drinks", tags = {
                        "getAllDrinks" })
        public List<Drink> getAllDrinks() {
                return drinkService.findAll();
        }

        @GetMapping("/near-me")
        @Operation(summary = "Drinks 3: Get All Available Drinks and Nearest Cargo", description = "Get a list of all available drinks and nearest cargo company to the client within 3km based on the client's location", tags = {
                        "getAllAvailableDrinksAndNearestCargoWithin" })
        public Map<String, Object> getAllAvailableDrinksAndNearestCargoWithin(
                        @RequestParam(name = "clientId") Long clientId,
                        @RequestParam(name = "distance") Double distance) {

                Map<String, Object> map = new HashMap<>();

                List<Drink> availableDrinks = drinkService.findAllAvailable();

                NearestCargoView nearestCargoDto = cargoService.findNearestCargoWithin(clientId, distance);

                map.put("availableDrinks", availableDrinks);
                map.put("nearestCargo", nearestCargoDto);

                return map;

        }

        @GetMapping("/{id}")
        @Operation(summary = "Drinks 4: Get Specific Drink by ID", description = "Get a specific drink by ID", tags = {
                        "getDrinkById" })
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Found the drink", content = {
                                        @Content(mediaType = "application/json", schema = @Schema(implementation = Drink.class)) }),
                        @ApiResponse(responseCode = "404", description = "Drink not found", content = @Content) })
        public ResponseEntity<Drink> getDrinkById(@PathVariable("id") Long id) {
                Drink drink = drinkService.findDrinkById(id);

                if (drink != null) {
                        return ResponseEntity.ok().body(drink);
                }

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        @GetMapping("/most-consumed")
        @Operation(summary = "Drinks 2: Get Top Most Consumed Drinks With Quantity", description = "Get a list of most consumed drinks and quantity", tags = {
                        "getTopConsumedDrinksWithQuantity" })
        public List<TopConsumedDrinkView> getTopConsumedDrinksWithQuantity(@RequestParam(name = "top") Integer top) {
                return drinkService.getTopConsumedDrinksWithQuantity(top);
        }

}
