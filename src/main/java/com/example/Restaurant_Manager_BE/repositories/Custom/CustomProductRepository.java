package com.example.Restaurant_Manager_BE.repositories.Custom;

import com.example.Restaurant_Manager_BE.dto.ProductDTO;
import com.example.Restaurant_Manager_BE.entities.ProductEntity;

import java.util.List;

public interface CustomProductRepository {
    List<ProductEntity> getProduct_with_Price_from_Import();
    List<ProductDTO> getProduct_with_Price_from_Import_price_notNUll();
    List<Object[]> getStatisticProductByCategoryAndSoldQuantity(Long id);
}
