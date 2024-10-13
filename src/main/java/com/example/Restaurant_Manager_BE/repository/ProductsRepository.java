package com.example.Restaurant_Manager_BE.repository;

import com.example.Restaurant_Manager_BE.entity.ProductEntity;
import com.example.Restaurant_Manager_BE.repository.custom.ProductsRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductsRepository extends JpaRepository<ProductEntity, Long>, ProductsRepositoryCustom {
    List<ProductEntity> findByNameContaining(String name);
}
