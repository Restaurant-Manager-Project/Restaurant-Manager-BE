package com.example.Restaurant_Manager_BE.services;

import org.springframework.http.ResponseEntity;

import com.example.Restaurant_Manager_BE.dto.StatusProductDTO;
import com.example.Restaurant_Manager_BE.responses.APIResponse;

public interface StatusProductService {

    ResponseEntity<APIResponse> createStatusProduct(StatusProductDTO statusProductDTO);

    ResponseEntity<APIResponse> getById(Long id);

    // ResponseEntity<APIResponse> updateStatusProduct(Long id, StatusProductDTO
    // statusProductDTO);

    // ResponseEntity<APIResponse> deleteStatusProduct(Long id);

    // ResponseEntity<APIResponse> getByName(String name);

}
