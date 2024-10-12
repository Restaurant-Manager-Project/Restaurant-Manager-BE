package com.example.Restaurant_Manager_BE.Repository;

import com.example.Restaurant_Manager_BE.Entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductsRepository extends JpaRepository<ProductEntity, Long> {
    List<ProductEntity> findByNameContaining(String name);
}
