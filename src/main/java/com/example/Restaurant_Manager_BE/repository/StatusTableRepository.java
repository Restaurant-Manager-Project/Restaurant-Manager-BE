package com.example.Restaurant_Manager_BE.repository;

import com.example.Restaurant_Manager_BE.entity.StatusTableEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusTableRepository extends JpaRepository<StatusTableEntity, Long> {
}
