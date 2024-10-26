package com.example.Restaurant_Manager_BE.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Restaurant_Manager_BE.constants.MessageKeys;
import com.example.Restaurant_Manager_BE.dto.StatusProductDTO;
import com.example.Restaurant_Manager_BE.dto.SupplierDTO;
import com.example.Restaurant_Manager_BE.entities.StatusProductEntity;
import com.example.Restaurant_Manager_BE.exceptions.DataNotFoundException;
import com.example.Restaurant_Manager_BE.repositories.StatusProductRepository;
import com.example.Restaurant_Manager_BE.responses.APIResponse;
import com.example.Restaurant_Manager_BE.services.StatusProductService;
import com.example.Restaurant_Manager_BE.utils.LocalizationUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StatusProductImpl implements StatusProductService {
    private final StatusProductRepository statusProductRepository;
    private final ModelMapper modelMapper;
    private final LocalizationUtils localizationUtils;

    @Override
    public ResponseEntity<APIResponse> createStatusProduct(StatusProductDTO statusProductDTO) {
        StatusProductEntity statusProductEntity = StatusProductEntity.builder()
                .name(statusProductDTO.getName())
                .build();
        statusProductRepository.save(statusProductEntity);
        APIResponse apiResponse = new APIResponse();
        apiResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.STATUS_PRODUCT_CREATE_SUCCESS));
        apiResponse.setResult(statusProductEntity);
        return ResponseEntity.ok(apiResponse);
    }

    @Override
    public ResponseEntity<APIResponse> getById(Long id) {
        StatusProductEntity statusProductEntity = statusProductRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(
                        localizationUtils.getLocalizedMessage(MessageKeys.SUPPLIER_NOT_EXISTED)));

        StatusProductDTO statusProductDTO = modelMapper.map(statusProductEntity, StatusProductDTO.class);
        APIResponse apiResponse = new APIResponse();
        apiResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.STATUS_PRODUCT_GET_SUCCESS));
        apiResponse.setResult(statusProductDTO);
        return ResponseEntity.ok(apiResponse);
    }

    // @Override
    // public ResponseEntity<APIResponse> getByName(String name){
    // List<StatusProductEntity> statusProductEntityList =
    // statusProductRepository.findByNameContaining(name);
    // List<StatusProductDTO> statusProductDTOList = new ArrayList<>();
    // statusProductEntityList.forEach(statusProduct ->{
    // StatusProductDTO statusProductDTO = modelMapper.map(statusProduct,
    // StatusProductDTO.class)
    // statusProductDTOList.add(statusProductDTO);
    // });
    // APIResponse apiResponse = new APIResponse();
    // apiResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.STATUS_PRODUCT_GET_SUCCESS));
    // }
}
