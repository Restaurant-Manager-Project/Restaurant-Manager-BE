package com.example.Restaurant_Manager_BE.service;

import com.example.Restaurant_Manager_BE.entity.OrderEntity;
import com.example.Restaurant_Manager_BE.exception.MessageRespone;
import com.example.Restaurant_Manager_BE.model.OrderModel;

public interface OrdersService {
    MessageRespone createOrder(OrderModel orderModel);
    MessageRespone updateOrder(OrderEntity orderEntity);

}
