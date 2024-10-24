package com.example.Restaurant_Manager_BE.services;

import com.example.Restaurant_Manager_BE.dto.ClientDTO;
import com.example.Restaurant_Manager_BE.entities.ClientEntity;
import com.example.Restaurant_Manager_BE.responses.APIResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

public interface ClientService {
    ResponseEntity<APIResponse> findByPhone(String phone);
    ResponseEntity<APIResponse> createClient(ClientDTO clientDTO);
}
