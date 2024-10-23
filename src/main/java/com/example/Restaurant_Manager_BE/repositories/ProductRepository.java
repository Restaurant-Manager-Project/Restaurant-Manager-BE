package com.example.Restaurant_Manager_BE.repositories;

import com.example.Restaurant_Manager_BE.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findByNameContaining(String name);

}
