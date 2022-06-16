package com.shop.service.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shop.service.models.Drink;

@Repository
public interface DrinkRepository extends JpaRepository<Drink, Long> {

    List<Drink> findByQuantityGreaterThan(Integer quantity);

    Drink findByCode(String code);

}
