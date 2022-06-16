package com.shop.service.services.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shop.service.dtos.CreateNewOrderRequestDto;
import com.shop.service.dtos.OrderDrinkDto;
import com.shop.service.models.Cargo;
import com.shop.service.models.Client;
import com.shop.service.models.Drink;
import com.shop.service.models.Order;
import com.shop.service.models.OrderDrink;
import com.shop.service.repositories.CargoRepository;
import com.shop.service.repositories.ClientRepository;
import com.shop.service.repositories.DrinkRepository;
import com.shop.service.repositories.OrderDrinkRepository;
import com.shop.service.repositories.OrderRepository;
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

}
