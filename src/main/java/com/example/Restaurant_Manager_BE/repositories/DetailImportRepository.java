package com.example.Restaurant_Manager_BE.repositories;

import com.example.Restaurant_Manager_BE.entities.DetailsImportEntity;
import com.example.Restaurant_Manager_BE.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DetailImportRepository extends JpaRepository<DetailsImportEntity, Long> {
    @Query("SELECT d FROM DetailsImportEntity d WHERE d.product = :product")
    Optional<DetailsImportEntity> findByProduct(@Param("product") ProductEntity product_id);
}
