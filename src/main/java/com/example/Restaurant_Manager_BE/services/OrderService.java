package com.example.Restaurant_Manager_BE.services;

import com.example.Restaurant_Manager_BE.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    boolean createOrder(OrderDTO orderDTO);

    List<OrderDTO> getOrdersByDirection(String direction);

}
