package com.example.Restaurant_Manager_BE.services;

import com.example.Restaurant_Manager_BE.dto.request.OrderRequest;
import com.example.Restaurant_Manager_BE.dto.response.OrderResponse;

import java.util.List;

public interface OrderService {
    boolean createOrder(OrderRequest orderRequest);

    OrderResponse getOrdersByDirection(String direction);

    List<OrderResponse> getAllOrders();

    OrderResponse getOrderById(Long id);

    boolean updateOrder(Long id, OrderRequest orderRequest);

}
