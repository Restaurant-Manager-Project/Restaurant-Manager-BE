package com.example.Restaurant_Manager_BE.services.impl;

import java.util.List;
import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Restaurant_Manager_BE.constants.MessageKeys;
import com.example.Restaurant_Manager_BE.dto.SupplierDTO;
import com.example.Restaurant_Manager_BE.entities.SupplierEntity;
import com.example.Restaurant_Manager_BE.repositories.SupplierRepository;
import com.example.Restaurant_Manager_BE.responses.APIResponse;
import com.example.Restaurant_Manager_BE.services.SupplierService;
import com.example.Restaurant_Manager_BE.utils.LocalizationUtils;

import io.micrometer.core.ipc.http.HttpSender.Response;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {
    private final LocalizationUtils localizationUtils;
    private final ModelMapper modelMapper;
    private final SupplierRepository supplierRepository;

    @Override
    public ResponseEntity<APIResponse> getAll() {
        List<SupplierEntity> supplierEntityList = supplierRepository.findAll();
        List<SupplierDTO> supplierDTOList = new ArrayList<>();
        supplierEntityList.forEach(supplierEntity -> {
            SupplierDTO supplier = modelMapper.map(supplierEntity, SupplierDTO.class);
            supplierDTOList.add(supplier);
        });
        APIResponse apiResponse = new APIResponse();
        apiResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.SUPPLIER_LIST_GET_SUCCESS));
        apiResponse.setResult(supplierDTOList);
        return ResponseEntity.ok(apiResponse);
    }

    @Override
    public ResponseEntity<APIResponse> getByName(String name) {
        List<SupplierEntity> supplierEntityList = supplierRepository.findByNameContaining(name);
        List<SupplierDTO> supplierDTOList = new ArrayList<>();
        supplierEntityList.forEach(supplierEntity -> {
            SupplierDTO supplier = modelMapper.map(supplierEntity, SupplierDTO.class);
            supplierDTOList.add(supplier);
        });
        APIResponse apiResponse = new APIResponse();
        apiResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.SUPPLIER_GET_SUCCESS));
        apiResponse.setResult(supplierDTOList);
        return ResponseEntity.ok(apiResponse);
    }

    // @Override
    // public ResponseEntity<APIResponse> createSupplier(SupplierDTO supplierDTO) {

    // }
}
