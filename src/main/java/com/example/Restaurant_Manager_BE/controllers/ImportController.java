package com.example.Restaurant_Manager_BE.controllers;

import com.example.Restaurant_Manager_BE.dto.ImportDTO;
import com.example.Restaurant_Manager_BE.responses.APIResponse;
import com.example.Restaurant_Manager_BE.services.ImportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequiredArgsConstructor
class ImportController {
    private final ImportService importService;
    @PreAuthorize("hasAuthority('import.create')")
    @PostMapping("/api/imports")
    public ResponseEntity<APIResponse> createImport(@RequestBody ImportDTO importDTO) {
        return importService.createImport(importDTO);
    }
    @PreAuthorize("hasAuthority('import.view')")
    @GetMapping("/api/imports")
    public ResponseEntity<APIResponse> getAllImport() {
        return importService.getAllImport();
    }
    @PreAuthorize("hasAuthority('import.view')")
    @GetMapping("/api/imports/{id}")
    public ResponseEntity<APIResponse> getImportById(@PathVariable Long id) {
        return importService.getImportById(id);
    }
    @PreAuthorize("hasAuthority('import.update')")
    @PutMapping("/api/imports/{id}")
    public ResponseEntity<APIResponse> updateImport(@PathVariable Long id, @RequestBody ImportDTO importDTO) {
        return importService.updateImport(id, importDTO);
    }

}