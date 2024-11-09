package com.example.Restaurant_Manager_BE.repositories;

import com.example.Restaurant_Manager_BE.entities.ImportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ImportRepository extends JpaRepository<ImportEntity, Long> {
    @Query("SELECT i FROM ImportEntity i JOIN FETCH i.employee JOIN FETCH i.supplier")
    List<ImportEntity> getAllWithEmployeeAndSupplier();
}
