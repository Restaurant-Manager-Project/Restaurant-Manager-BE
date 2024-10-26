package com.example.Restaurant_Manager_BE.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Restaurant_Manager_BE.entities.StatusProductEntity;

@Repository
public interface StatusProductRepository extends JpaRepository<StatusProductEntity, Long> {
    List<StatusProductEntity> findByNameContaining(String name);
}
