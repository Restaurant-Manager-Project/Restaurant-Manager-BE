package com.example.Restaurant_Manager_BE.services;

import com.example.Restaurant_Manager_BE.dto.request.ImportRequest;
import com.example.Restaurant_Manager_BE.dto.response.ImportResponse;

import java.util.List;


public interface ImportService {
    boolean createImport(ImportRequest importRequest);
    ImportResponse getImportById(Long id);
    List<ImportResponse> getAllImport();

    boolean updateImport(Long id, ImportRequest importRequest);
}
