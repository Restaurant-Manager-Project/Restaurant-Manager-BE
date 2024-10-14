package com.example.Restaurant_Manager_BE.service;

import com.example.Restaurant_Manager_BE.model.ProductsModel;

import java.util.List;

public interface ProductsService {
    public List<ProductsModel> getAll();
    public List<ProductsModel> getByName(String name);
    void test();
}
