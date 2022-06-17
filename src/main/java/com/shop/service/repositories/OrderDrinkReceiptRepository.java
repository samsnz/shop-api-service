package com.shop.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shop.service.models.OrderDrinkReceipt;

@Repository
public interface OrderDrinkReceiptRepository extends JpaRepository<OrderDrinkReceipt, Long> {

}
