package com.example.Restaurant_Manager_BE.repositories.Custom;

import com.example.Restaurant_Manager_BE.entities.OrderEntity;

import java.util.List;

public interface CustomOrderRepository {
    List<OrderEntity> getAllOrderWithDetailsByDirectionTable(String directionTable);
    List<OrderEntity> getAllOrderWithTableAndProcess();
}
