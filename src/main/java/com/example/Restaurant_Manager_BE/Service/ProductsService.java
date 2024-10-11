package com.example.Restaurant_Manager_BE.Service;

import com.example.Restaurant_Manager_BE.Entity.ProductEntity;
import com.example.Restaurant_Manager_BE.Model.ProductsModel;

import java.util.List;

public interface ProductsService {
    public List<ProductsModel> getAll();
}
