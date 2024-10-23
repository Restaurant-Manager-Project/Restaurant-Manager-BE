package com.example.Restaurant_Manager_BE.services;

import com.example.Restaurant_Manager_BE.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    public List<ProductDTO> getAll();
    public List<ProductDTO> getByName(String name);

    public ProductDTO getById(Long id);
}
