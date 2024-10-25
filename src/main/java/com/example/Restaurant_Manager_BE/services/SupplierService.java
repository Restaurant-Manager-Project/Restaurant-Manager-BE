package com.example.Restaurant_Manager_BE.services;

import org.springframework.http.ResponseEntity;

import com.example.Restaurant_Manager_BE.dto.SupplierDTO;
import com.example.Restaurant_Manager_BE.responses.APIResponse;

public interface SupplierService {
    ResponseEntity<APIResponse> getAll();

    ResponseEntity<APIResponse> getByName(String name);

    ResponseEntity<APIResponse> getById(Long id);

    ResponseEntity<APIResponse> createSupplier(SupplierDTO supplierDTO);
}
