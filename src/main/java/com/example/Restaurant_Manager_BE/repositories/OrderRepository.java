package com.example.Restaurant_Manager_BE.repositories;

import com.example.Restaurant_Manager_BE.entities.OrderEntity;
import jakarta.annotation.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    Optional<List<OrderEntity>> findByDirectionTable(String direction);
}
