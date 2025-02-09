package com.example.Restaurant_Manager_BE.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import com.example.Restaurant_Manager_BE.dto.request.SupplierRequest;
import com.example.Restaurant_Manager_BE.dto.response.SupplierResponse;
import com.example.Restaurant_Manager_BE.mapper.request.SupplierRequestMapper;
import com.example.Restaurant_Manager_BE.mapper.response.SupplierResponseMapper;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Restaurant_Manager_BE.constants.MessageKeys;
import com.example.Restaurant_Manager_BE.dto.SupplierDTO;
import com.example.Restaurant_Manager_BE.entities.SupplierEntity;
import com.example.Restaurant_Manager_BE.exceptions.DataNotFoundException;
import com.example.Restaurant_Manager_BE.repositories.SupplierRepository;
import com.example.Restaurant_Manager_BE.responses.APIResponse;
import com.example.Restaurant_Manager_BE.services.SupplierService;
import com.example.Restaurant_Manager_BE.utils.LocalizationUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {
    private final LocalizationUtils localizationUtils;
    private final SupplierRepository supplierRepository;
    private final SupplierResponseMapper supplierResponseMapper;
    private final SupplierRequestMapper supplierRequestMapper;

    @Override
    public List<SupplierResponse> getAll() {
        List<SupplierEntity> supplierEntityList = supplierRepository.findAll();
        return supplierResponseMapper.toListDto(supplierEntityList);
    }

    @Override
    public List<SupplierResponse> getByName(String name) {
        List<SupplierEntity> supplierEntityList = supplierRepository.findByNameContaining(name);
        return supplierResponseMapper.toListDto(supplierEntityList);
    }

    @Override
    public SupplierResponse getById(Long id) {
        SupplierEntity supplierEntity = supplierRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(
                        localizationUtils.getLocalizedMessage(MessageKeys.SUPPLIER_NOT_EXISTED)));
        return supplierResponseMapper.toDto(supplierEntity);
    }

    @Override
    public boolean createSupplier(SupplierRequest supplierRequest) {
        SupplierEntity supplierEntity = supplierRequestMapper.toEntity(supplierRequest);
        return supplierRepository.save(supplierEntity) != null ? true : false;
    }

    @Override
    public boolean deleteSupplier(Long supplierId) {
        SupplierEntity supplierEntity = supplierRepository.findById(supplierId)
                .orElseThrow(() -> new DataNotFoundException(
                        localizationUtils.getLocalizedMessage(MessageKeys.SUPPLIER_NOT_EXISTED)));
        supplierEntity.setIsDeleted(true);
        return supplierRepository.save(supplierEntity) != null ? true : false;
    }

    @Override
    public boolean updateSupplier(Long id, SupplierRequest supplierRequest) {
        SupplierEntity supplierEntity = supplierRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(
                        localizationUtils.getLocalizedMessage(MessageKeys.SUPPLIER_NOT_EXISTED)));
        supplierRequestMapper.update(supplierEntity, supplierRequest);
        return supplierRepository.save(supplierEntity) != null ? true : false;
    }

}
