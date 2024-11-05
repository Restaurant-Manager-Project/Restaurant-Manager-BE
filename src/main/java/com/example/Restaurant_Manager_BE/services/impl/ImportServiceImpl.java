package com.example.Restaurant_Manager_BE.services.impl;

import com.example.Restaurant_Manager_BE.constants.MessageKeys;
import com.example.Restaurant_Manager_BE.converters.ConverterImport;
import com.example.Restaurant_Manager_BE.dto.ImportDTO;
import com.example.Restaurant_Manager_BE.entities.ImportEntity;
import com.example.Restaurant_Manager_BE.exceptions.DataNotFoundException;
import com.example.Restaurant_Manager_BE.exceptions.InvalidInputException;
import com.example.Restaurant_Manager_BE.repositories.ImportRepository;
import com.example.Restaurant_Manager_BE.responses.APIResponse;
import com.example.Restaurant_Manager_BE.services.ImportService;
import com.example.Restaurant_Manager_BE.utils.LocalizationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ImportServiceImpl implements ImportService {

    private final ImportRepository importRepository;
    private final ConverterImport converterImport;
    private final LocalizationUtils localizationUtils;


    @Override
    public ResponseEntity<APIResponse> createImport(ImportDTO importDTO) {
        ImportEntity importEntity = converterImport.toEntity(importDTO);
        importEntity.setIsDeleted(false);
        if (importEntity == null) {
            throw new InvalidInputException(MessageKeys.INVALID_INPUT);
        }
        APIResponse APIResponse = new APIResponse();
        APIResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.IMPORT_CREATE_SUCCESS));
        importRepository.save(importEntity);
        return ResponseEntity.ok(APIResponse);
    }

    @Override
    public ResponseEntity<APIResponse> getImportById(Long id) {
        ImportEntity importEntity = importRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(MessageKeys.IMPORT_NOT_EXISTED));
        APIResponse APIResponse = new APIResponse();
        APIResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.IMPORT_GET_SUCCESS));
        APIResponse.setResult(importEntity);
        return ResponseEntity.ok(APIResponse);
    }

    @Override
    public ResponseEntity<APIResponse> getAllImport() {
        List<ImportEntity> listImport = importRepository.findAll();
        APIResponse APIResponse = new APIResponse();
        APIResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.IMPORT_GET_ALL_SUCCESS));
        APIResponse.setResult(listImport);
        return ResponseEntity.ok(APIResponse);
    }

    @Override
    public ResponseEntity<APIResponse> updateImport(Long id, ImportDTO importDTO) {
        ImportEntity importEntity = importRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(MessageKeys.IMPORT_NOT_EXISTED));
        ImportEntity importEntityUpdate = converterImport.toEntity(importDTO);
        converterImport.mergeNonNullFields(importEntity, importEntityUpdate);
        importRepository.save(importEntity);
        APIResponse APIResponse = new APIResponse();
        APIResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.IMPORT_UPDATE_SUCCESS));
        return ResponseEntity.ok(APIResponse);
    }
}
