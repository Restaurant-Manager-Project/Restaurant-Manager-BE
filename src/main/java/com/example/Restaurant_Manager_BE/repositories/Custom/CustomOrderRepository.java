package com.example.Restaurant_Manager_BE.repositories.Custom;

import com.example.Restaurant_Manager_BE.entities.OrderEntity;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface CustomOrderRepository {
    List<OrderEntity> getAllOrderWithDetailsByDirectionTable(String directionTable);
    List<OrderEntity> getAllOrderWithTableAndProcess();
}
