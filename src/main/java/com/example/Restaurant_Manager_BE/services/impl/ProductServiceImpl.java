package com.example.Restaurant_Manager_BE.services.impl;

import com.example.Restaurant_Manager_BE.constants.MessageKeys;
import com.example.Restaurant_Manager_BE.entities.ProductEntity;
import com.example.Restaurant_Manager_BE.dto.ProductDTO;
import com.example.Restaurant_Manager_BE.exceptions.DataNotFoundException;
import com.example.Restaurant_Manager_BE.repositories.ProductRepository;
import com.example.Restaurant_Manager_BE.responses.APIResponse;
import com.example.Restaurant_Manager_BE.services.ProductService;
import com.example.Restaurant_Manager_BE.utils.LocalizationUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final LocalizationUtils localizationUtils;
    @Override
    public ResponseEntity<APIResponse> getAll() {
        List<ProductEntity> productEntityList = productRepository.findAll();
        List<ProductDTO> productDTOList = new ArrayList<>();
        productEntityList.forEach(productEntity -> {
            ProductDTO product = modelMapper.map(productEntity, ProductDTO.class);
            productDTOList.add(product);
        });
        APIResponse APIResponse = new APIResponse();
        APIResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.PRODUCT_LIST_GET_SUCCESS));
        APIResponse.setResult(productDTOList);

        return ResponseEntity.ok(APIResponse);

    }

    @Override
    public ResponseEntity<APIResponse> getByName(String name) {
        List<ProductEntity> productEntityList = productRepository.findByNameContaining(name);
        List<ProductDTO> productDTOList = new ArrayList<>();
        productEntityList.forEach(productEntity -> {
            ProductDTO product = modelMapper.map(productEntity, ProductDTO.class);
            productDTOList.add(product);
        });
        APIResponse APIResponse = new APIResponse();
        APIResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.PRODUCT_GET_SUCCESS));
        APIResponse.setResult(productDTOList);
        return ResponseEntity.ok(APIResponse);
    }

    @Override
    public ResponseEntity<APIResponse> getById(Long id) {
        ProductEntity productEntity = productRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKeys.PRODUCT_NOT_EXISTED)));
        ProductDTO product = modelMapper.map(productEntity, ProductDTO.class);
        product.setCategoryName(productEntity.getCategory().getName());
        APIResponse APIResponse = new APIResponse();
        APIResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.PRODUCT_GET_SUCCESS));
        APIResponse.setResult(product);
        return ResponseEntity.ok(APIResponse);
    }

}
