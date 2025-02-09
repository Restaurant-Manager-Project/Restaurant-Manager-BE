package com.example.Restaurant_Manager_BE.controllers;

import com.example.Restaurant_Manager_BE.constants.MessageKeys;
import com.example.Restaurant_Manager_BE.dto.request.ImportRequest;
import com.example.Restaurant_Manager_BE.responses.APIResponse;
import com.example.Restaurant_Manager_BE.services.ImportService;
import com.example.Restaurant_Manager_BE.utils.LocalizationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequiredArgsConstructor
class ImportController {
    private final ImportService importService;
    private final LocalizationUtils localizationUtils;
    @PreAuthorize("hasAuthority('import.create')")
    @PostMapping("/api/imports")
    public ResponseEntity<APIResponse> createImport(@RequestBody ImportRequest importRequest) {
        String message = importService.createImport(importRequest) ?
                localizationUtils.getLocalizedMessage(MessageKeys.IMPORT_CREATE_SUCCESS) :
                localizationUtils.getLocalizedMessage(MessageKeys.IMPORT_CREATE_FAILED);

        return ResponseEntity.ok(APIResponse.builder()
                .message(message)
                .build());
    }
    @PreAuthorize("hasAuthority('import.view')")
    @GetMapping("/api/imports")
    public ResponseEntity<APIResponse> getAllImport() {
        return ResponseEntity.ok(APIResponse.builder()
                .message(localizationUtils.getLocalizedMessage(MessageKeys.IMPORT_GET_ALL_SUCCESS))
                .result(importService.getAllImport())
                .build());
    }
    @PreAuthorize("hasAuthority('import.view')")
    @GetMapping("/api/imports/{id}")
    public ResponseEntity<APIResponse> getImportById(@PathVariable Long id) {
        String message = importService.getImportById(id) != null ?
                localizationUtils.getLocalizedMessage(MessageKeys.IMPORT_GET_SUCCESS) :
                localizationUtils.getLocalizedMessage(MessageKeys.IMPORT_GET_FAILED);
        return ResponseEntity.ok(APIResponse.builder()
                .message(message)
                .build());
    }
    @PreAuthorize("hasAuthority('import.update')")
    @PutMapping("/api/imports/{id}")
    public ResponseEntity<APIResponse> updateImport(@PathVariable Long id, @RequestBody ImportRequest importRequest) {
        String message = importService.updateImport(id, importRequest) ?
                localizationUtils.getLocalizedMessage(MessageKeys.IMPORT_UPDATE_SUCCESS) :
                localizationUtils.getLocalizedMessage(MessageKeys.IMPORT_GET_FAILED);
        return ResponseEntity.ok(APIResponse.builder()
                .message(message)
                .build());
    }

}