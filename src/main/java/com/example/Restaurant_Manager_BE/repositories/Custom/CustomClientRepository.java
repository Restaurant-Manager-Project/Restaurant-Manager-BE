package com.example.Restaurant_Manager_BE.repositories.Custom;

import com.example.Restaurant_Manager_BE.dto.ClientDTO;
import com.example.Restaurant_Manager_BE.repositories.ClientRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

public interface CustomClientRepository {
    List<ClientDTO> findAll_fromClient_andInvoice();
}
