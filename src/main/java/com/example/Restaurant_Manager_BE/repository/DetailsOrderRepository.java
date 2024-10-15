package com.example.Restaurant_Manager_BE.repository;

import com.example.Restaurant_Manager_BE.entity.DetailsOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetailsOrderRepository extends JpaRepository<DetailsOrderEntity, Long> {
}
