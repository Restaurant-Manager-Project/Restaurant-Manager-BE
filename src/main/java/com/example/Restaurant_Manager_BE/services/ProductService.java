package com.example.Restaurant_Manager_BE.services;

import com.example.Restaurant_Manager_BE.dto.ProductDTO;
import com.example.Restaurant_Manager_BE.entities.ProductEntity;
import com.example.Restaurant_Manager_BE.responses.APIResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    ResponseEntity<APIResponse> getAll();

    ResponseEntity<APIResponse> getByName(String name);
    ResponseEntity<APIResponse> createProducts(ProductDTO productDTO);
    ResponseEntity<APIResponse> getById(Long id);
    ResponseEntity<APIResponse> deleteProducts(Long id);
    ResponseEntity<APIResponse> updateProducts(Long id , ProductDTO productDTO);
    ResponseEntity<APIResponse> StatisticProductByCategoryAndSoldQuantity(Long id);
}
