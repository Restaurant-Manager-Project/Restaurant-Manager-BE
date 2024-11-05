package com.example.Restaurant_Manager_BE.services;

import com.example.Restaurant_Manager_BE.dto.ImportDTO;
import com.example.Restaurant_Manager_BE.responses.APIResponse;
import org.springframework.http.ResponseEntity;


public interface ImportService {
    ResponseEntity<APIResponse> createImport(ImportDTO importDTO);
    ResponseEntity<APIResponse> getImportById(Long id);
    ResponseEntity<APIResponse> getAllImport();

    ResponseEntity<APIResponse> updateImport(Long id, ImportDTO importDTO);
}
