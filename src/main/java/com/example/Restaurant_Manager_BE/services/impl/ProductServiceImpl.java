package com.example.Restaurant_Manager_BE.services.impl;

import com.example.Restaurant_Manager_BE.entities.ProductEntity;
import com.example.Restaurant_Manager_BE.dto.ProductDTO;
import com.example.Restaurant_Manager_BE.repositories.ProductRepository;
import com.example.Restaurant_Manager_BE.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    ProductRepository productRepository;
    @Override
    public List<ProductDTO> getAll() {
        List<ProductEntity> productEntityList = productRepository.findAll();
        List<ProductDTO> productDTOList = new ArrayList<>();

        for (ProductEntity x : productEntityList) {
            ProductDTO product = new ModelMapper().map(x, ProductDTO.class);
            productDTOList.add(product);
        }

        return productDTOList;

    }

    @Override
    public List<ProductDTO> getByName(String name) {
        List<ProductEntity> productEntityList = productRepository.findByNameContaining(name);
        List<ProductDTO> productDTOList = new ArrayList<>();

        for (ProductEntity x : productEntityList) {
            ProductDTO product = new ModelMapper().map(x, ProductDTO.class);
            productDTOList.add(product);
        }

        return productDTOList;
    }

    @Override
    public ProductDTO getById(Long id) {
        ProductEntity productEntity = productRepository.findById(id).get();
        ModelMapper modelMapper = new ModelMapper();
        ProductDTO product = modelMapper.map(productEntity, ProductDTO.class);
        product.setCategoryName(productEntity.getCategory().getName());
        return product;
    }

}
