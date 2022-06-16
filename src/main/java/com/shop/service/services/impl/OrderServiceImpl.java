package com.shop.service.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shop.service.dtos.CreateNewOrderRequestDto;
import com.shop.service.dtos.OrderDrinkDto;
import com.shop.service.dtos.OrderTotalPriceDto;
import com.shop.service.dtos.views.OrderTotalPriceView;
import com.shop.service.models.Cargo;
import com.shop.service.models.Client;
import com.shop.service.models.Drink;
import com.shop.service.models.Order;
import com.shop.service.models.OrderDrink;
import com.shop.service.models.OrderDrinkReceipt;
import com.shop.service.models.Receipt;
import com.shop.service.models.enums.EOrderStatus;
import com.shop.service.repositories.CargoRepository;
import com.shop.service.repositories.ClientRepository;
import com.shop.service.repositories.DrinkRepository;
import com.shop.service.repositories.OrderDrinkReceiptRepository;
import com.shop.service.repositories.OrderDrinkRepository;
import com.shop.service.repositories.OrderRepository;
import com.shop.service.repositories.ReceiptRepository;
import com.shop.service.services.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private CargoRepository cargoRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private DrinkRepository drinkRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDrinkRepository orderDrinkRepository;

    @Autowired
    private ReceiptRepository receiptRepository;

    @Autowired
    private OrderDrinkReceiptRepository orderDrinkReceiptRepository;

    @Override
    public Order findById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order save(CreateNewOrderRequestDto createNewOrderRequestDto) {

        Cargo cargo = cargoRepository.findByCode(createNewOrderRequestDto.getCargoCode());

        Client client = clientRepository.findById(createNewOrderRequestDto.getClientId()).orElse(null);

        List<OrderDrinkDto> orderDrinksList = createNewOrderRequestDto.getOrderDrinksList();

        Order orderToSave = new Order();

        orderToSave.setCargo(cargo);
        orderToSave.setClient(client);
        orderToSave.setDetails(createNewOrderRequestDto.getOrderDetails());

        Order savedOrder = orderRepository.save(orderToSave);

        orderDrinksList.forEach(item -> {

            Drink drink = drinkRepository.findByCode(item.getDrinkCode());

            if (drink.getQuantity() - item.getQuantityOrdered() < 0) {
                throw new RuntimeException("Quantity ordered is large");
            }

            OrderDrink orderDrink = new OrderDrink();

            orderDrink.setDrink(drink);
            orderDrink.setOrder(savedOrder);
            orderDrink.setQuantityOrdered(item.getQuantityOrdered());

            drink.setQuantity(drink.getQuantity() - item.getQuantityOrdered());

            drinkRepository.save(drink);

            orderDrinkRepository.save(orderDrink);
        });

        return savedOrder;

    }

    @Override
    public List<Order> findTop5RecentRowsForDifferentClients() {
        return orderRepository.findTop5RecentRowsForDifferentClients();
    }

    @Override
    public List<OrderTotalPriceDto> findTop10PaidOrdersWithClientAndTransporterDetails() {

        List<OrderTotalPriceView> orderTotalPriceViews = orderRepository
                .findTop10PaidOrdersWithClientAndTransporterDetails();

        List<OrderTotalPriceDto> orderTotalPriceDtos = new ArrayList<>();

        orderTotalPriceViews.forEach(item -> {
            Order order = findById(item.getOrderId());

            OrderTotalPriceDto orderTotalPriceDto = new OrderTotalPriceDto();

            orderTotalPriceDto.setId(order.getId());
            orderTotalPriceDto.setDetails(order.getDetails());
            orderTotalPriceDto.setDate(order.getDate());
            orderTotalPriceDto.setStatus(order.getStatus());
            orderTotalPriceDto.setClient(order.getClient());
            orderTotalPriceDto.setCargo(order.getCargo());
            orderTotalPriceDto.setOrderDrinks(order.getOrderDrinks());
            orderTotalPriceDto.setTotalPrice(item.getTotalPrice());

            orderTotalPriceDtos.add(orderTotalPriceDto);
        });

        return orderTotalPriceDtos;

    }

    @Override
    public Receipt completeOrder(Long orderId) {
        Order orderToComplete = findById(orderId);

        orderToComplete.setStatus(EOrderStatus.COMPLETED);

        Order savedOrder = orderRepository.save(orderToComplete);

        Receipt receipt = new Receipt();

        receipt.setOrderId(savedOrder.getId());
        receipt.setOrderDetails(savedOrder.getDetails());
        receipt.setOrderDate(savedOrder.getDate());
        receipt.setClientId(savedOrder.getClient().getId());
        receipt.setClientDetails(savedOrder.getClient().getDetails());
        receipt.setClientLongitude(savedOrder.getClient().getLongitude());
        receipt.setClientLatitude(savedOrder.getClient().getLatitude());
        receipt.setCargoId(savedOrder.getCargo().getId());
        receipt.setCargoCode(savedOrder.getCargo().getCode());
        receipt.setCargoName(savedOrder.getCargo().getName());
        receipt.setCargoDetails(savedOrder.getCargo().getDetails());
        receipt.setCargoLongitude(savedOrder.getCargo().getLongitude());
        receipt.setCargoLatitude(savedOrder.getCargo().getLatitude());

        Receipt savedReceipt = receiptRepository.save(receipt);

        savedOrder.getOrderDrinks().forEach(item -> {
            OrderDrinkReceipt orderDrinkReceipt = new OrderDrinkReceipt();

            orderDrinkReceipt.setDrinkId(item.getDrink().getId());
            orderDrinkReceipt.setDrinkCode(item.getDrink().getCode());
            orderDrinkReceipt.setDrinkName(item.getDrink().getName());
            orderDrinkReceipt.setDrinkQuantity(item.getQuantityOrdered());
            orderDrinkReceipt.setDrinkUnitPrice(item.getDrink().getUnitPrice());
            orderDrinkReceipt.setReceipt(savedReceipt);

            orderDrinkReceiptRepository.save(orderDrinkReceipt);
        });

        return savedReceipt;

    }

}
