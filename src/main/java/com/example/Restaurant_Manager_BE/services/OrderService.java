package com.example.Restaurant_Manager_BE.services;

import com.example.Restaurant_Manager_BE.dto.OrderDTO;
import com.example.Restaurant_Manager_BE.responses.APIResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderService {
    ResponseEntity<APIResponse> createOrder(OrderDTO orderDTO);
    ResponseEntity<APIResponse> getOrdersByDirection(String direction);

}
