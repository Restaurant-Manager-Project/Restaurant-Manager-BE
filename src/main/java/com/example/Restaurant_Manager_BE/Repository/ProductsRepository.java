package com.example.Restaurant_Manager_BE.Repository;

import com.example.Restaurant_Manager_BE.Entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<ProductEntity, Long> {

}
