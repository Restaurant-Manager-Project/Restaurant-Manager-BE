package com.example.Restaurant_Manager_BE.service;

import com.example.Restaurant_Manager_BE.entity.OrderEntity;
import com.example.Restaurant_Manager_BE.exception.MessageResponse;
import com.example.Restaurant_Manager_BE.model.OrderModel;

public interface OrdersService {
    boolean createOrder(OrderModel orderModel);
    MessageResponse updateOrder(OrderEntity orderEntity);

}
