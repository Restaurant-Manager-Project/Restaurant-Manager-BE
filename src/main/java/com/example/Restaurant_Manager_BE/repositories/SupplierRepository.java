package com.example.Restaurant_Manager_BE.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Restaurant_Manager_BE.entities.SupplierEntity;

@Repository
public interface SupplierRepository extends JpaRepository<SupplierEntity, Long> {
    List<SupplierEntity> findByNameContaining(String name);
}
