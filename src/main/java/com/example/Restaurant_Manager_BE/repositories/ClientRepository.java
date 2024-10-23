package com.example.Restaurant_Manager_BE.repositories;

import com.example.Restaurant_Manager_BE.entities.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
}
