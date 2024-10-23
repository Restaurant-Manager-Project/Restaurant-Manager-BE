package com.example.Restaurant_Manager_BE.repository;

import com.example.Restaurant_Manager_BE.entity.OrderEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findByDirectionTable(String direction);
}
