package com.example.Restaurant_Manager_BE.repositories;

import com.example.Restaurant_Manager_BE.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findByDirectionTable(String direction);
}
