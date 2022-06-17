package com.shop.service.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shop.service.dtos.views.OrderTotalPriceView;
import com.shop.service.models.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "SELECT MAX(id) AS id, date, details, status, cargo_code, client_id FROM orders GROUP BY client_id ORDER BY id DESC LIMIT 5", nativeQuery = true)
    List<Order> findTop5RecentRowsForDifferentClients();

    @Query(value = "SELECT a.order_id AS orderId, SUM(a.quantity_ordered * b.unit_price) AS totalPrice FROM soft_shop.order_drink a INNER JOIN soft_shop.drinks b ON a.drink_code = b.code GROUP BY a.order_id ORDER BY totalPrice DESC LIMIT 10", nativeQuery = true)
    List<OrderTotalPriceView> findTop10PaidOrdersWithClientAndTransporterDetails();

}
