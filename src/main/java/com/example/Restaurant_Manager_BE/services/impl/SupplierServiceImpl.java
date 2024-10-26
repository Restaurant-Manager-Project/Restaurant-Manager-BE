package com.example.Restaurant_Manager_BE.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

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

    @Override
    public ResponseEntity<APIResponse> getById(Long id) {
        SupplierEntity supplierEntity = supplierRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(
                        localizationUtils.getLocalizedMessage(MessageKeys.SUPPLIER_NOT_EXISTED)));

        SupplierDTO supplierDTO = modelMapper.map(supplierEntity, SupplierDTO.class);

        APIResponse apiResponse = new APIResponse();
        apiResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.SUPPLIER_GET_SUCCESS));
        apiResponse.setResult(supplierDTO);

        return ResponseEntity.ok(apiResponse);
    }

    @Override
    public ResponseEntity<APIResponse> createSupplier(SupplierDTO supplierDTO) {
        SupplierEntity supplierEntity = SupplierEntity.builder()
                .name(supplierDTO.getName())
                .address(supplierDTO.getAddress())
                .phone(supplierDTO.getPhone())
                .isDeleted(false)
                .build();
        supplierRepository.save(supplierEntity);
        APIResponse apiResponse = new APIResponse();
        apiResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.SUPPLIER_CREATE_SUCCESS));
        apiResponse.setResult(supplierEntity);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @Override
    public ResponseEntity<APIResponse> deleteSupplier(Long supplierId) {
        Optional<SupplierEntity> supplierOptional = supplierRepository.findById(supplierId);
        if (supplierOptional.isPresent()) {
            SupplierEntity supplierEntity = supplierOptional.get();
            supplierEntity.setIsDeleted(true);
            supplierRepository.save(supplierEntity);

            APIResponse apiResponse = new APIResponse();
            apiResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.SUPPLIER_DELETE_SUCCESS));
            return ResponseEntity.ok(apiResponse);
        } else {
            APIResponse apiResponse = new APIResponse();
            apiResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.SUPPLIER_NOT_EXISTED));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }
    }

    @Override
    public ResponseEntity<APIResponse> updateSupplier(SupplierDTO supplierDTO) {
        SupplierEntity supplierEntity = supplierRepository.findById(supplierDTO.getId())
                .orElseThrow(() -> new DataNotFoundException(
                        localizationUtils.getLocalizedMessage(MessageKeys.SUPPLIER_NOT_EXISTED)));

        supplierEntity.setAddress(supplierDTO.getAddress());
        supplierEntity.setName(supplierDTO.getName());
        supplierEntity.setPhone(supplierDTO.getPhone());

        supplierRepository.save(supplierEntity);

        APIResponse apiResponse = new APIResponse();
        apiResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.SUPPLIER_UPDATE_SUCCESS));
        apiResponse.setResult(supplierEntity);
        return ResponseEntity.ok(apiResponse);
    }

}
