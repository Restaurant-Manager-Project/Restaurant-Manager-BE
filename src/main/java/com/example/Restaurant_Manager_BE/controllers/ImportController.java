package com.example.Restaurant_Manager_BE.controllers;

import com.example.Restaurant_Manager_BE.dto.ImportDTO;
import com.example.Restaurant_Manager_BE.responses.APIResponse;
import com.example.Restaurant_Manager_BE.services.ImportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
class ImportController {
    private final ImportService importService;

    @PostMapping("/api/imports")
    public ResponseEntity<APIResponse> createImport(@RequestBody ImportDTO importDTO) {
        return importService.createImport(importDTO);
    }

    @GetMapping("/api/imports/{id}")
    public ResponseEntity<APIResponse> getImportById(@PathVariable Long id) {
        return importService.getImportById(id);
    }


}