package com.example.Restaurant_Manager_BE.repositories;

import com.example.Restaurant_Manager_BE.entities.ProductEntity;
import com.example.Restaurant_Manager_BE.repositories.Custom.CustomProductRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> , CustomProductRepository {
    List<ProductEntity> findByNameContaining(String name);

}
