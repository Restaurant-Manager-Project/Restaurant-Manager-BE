package com.example.Restaurant_Manager_BE.services;
import com.example.Restaurant_Manager_BE.responses.APIResponse;
import org.springframework.http.ResponseEntity;
import com.example.Restaurant_Manager_BE.dto.CategoriesDTO;


public interface CategoriesService {
    ResponseEntity<APIResponse> createCategories(CategoriesDTO categoryDTO);
}
