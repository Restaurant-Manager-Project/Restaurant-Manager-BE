package com.example.Restaurant_Manager_BE.repositories;

import com.example.Restaurant_Manager_BE.entities.StatusTableEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusTableRepository extends JpaRepository<StatusTableEntity, Long> {
}
