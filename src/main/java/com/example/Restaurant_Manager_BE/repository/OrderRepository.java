package com.example.Restaurant_Manager_BE.repository;

import com.example.Restaurant_Manager_BE.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

}
