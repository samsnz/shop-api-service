package com.shop.service.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shop.service.dtos.views.TopConsumedDrinkView;
import com.shop.service.models.Drink;

@Repository
public interface DrinkRepository extends JpaRepository<Drink, Long> {

    List<Drink> findByQuantityGreaterThan(Integer quantity);

    Drink findByCode(String code);

    @Query(value = "SELECT b.id, b.code, b.name, b.unit_price AS unitPrice, SUM(a.quantity_ordered) AS totalQuantityConsumed FROM order_drink a INNER JOIN drinks b ON a.drink_code = b.code GROUP BY a.drink_code ORDER BY totalQuantityConsumed DESC LIMIT :limit", nativeQuery = true)
    List<TopConsumedDrinkView> getTopConsumedDrinksWithQuantity(Integer limit);

}
