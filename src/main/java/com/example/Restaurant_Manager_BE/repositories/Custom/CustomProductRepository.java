package com.example.Restaurant_Manager_BE.repositories.Custom;

import com.example.Restaurant_Manager_BE.dto.ProductDTO;

import java.util.List;

public interface CustomProductRepository {
    List<ProductDTO> getProduct_with_Price_from_Import();
}
