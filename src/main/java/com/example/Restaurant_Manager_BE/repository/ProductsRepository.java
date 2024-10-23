package com.example.Restaurant_Manager_BE.repository;

import com.example.Restaurant_Manager_BE.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductsRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findByNameContaining(String name);

}
