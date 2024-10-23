package com.example.Restaurant_Manager_BE.services;

import com.example.Restaurant_Manager_BE.dto.ProductDTO;
import com.example.Restaurant_Manager_BE.responses.APIResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    ResponseEntity<APIResponse> getAll();
    ResponseEntity<APIResponse> getByName(String name);

    ResponseEntity<APIResponse> getById(Long id);
}
