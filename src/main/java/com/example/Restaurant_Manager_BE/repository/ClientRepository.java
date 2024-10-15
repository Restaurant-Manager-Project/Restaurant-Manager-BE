package com.example.Restaurant_Manager_BE.repository;

import com.example.Restaurant_Manager_BE.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
}
