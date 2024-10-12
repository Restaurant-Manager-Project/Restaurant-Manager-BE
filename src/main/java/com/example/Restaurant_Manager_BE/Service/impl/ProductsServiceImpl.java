package com.example.Restaurant_Manager_BE.Service.impl;

import com.example.Restaurant_Manager_BE.Entity.ProductEntity;
import com.example.Restaurant_Manager_BE.Model.ProductsModel;
import com.example.Restaurant_Manager_BE.Repository.ProductsRepository;
import com.example.Restaurant_Manager_BE.Service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ProductsServiceImpl implements ProductsService {
    @Autowired
    ProductsRepository productsRepository;
    @Override
    public List<ProductsModel> getAll() {
        List<ProductEntity> productEntityList = productsRepository.findAll();
        List<ProductsModel> productsModelList = new ArrayList<>();

        for (ProductEntity x : productEntityList) {
            ProductsModel product = new ProductsModel();
            product.setId(x.getId());
            product.setName(x.getName());
            product.setImg(x.getImg());
            product.setDescription(x.getDescription());
            productsModelList.add(product);
        }

        return productsModelList;

    }

    @Override
    public List<ProductsModel> getByName(String name) {
        List<ProductEntity> productEntityList = productsRepository.findByNameContaining(name);
        List<ProductsModel> productsModelList = new ArrayList<>();

        for (ProductEntity x : productEntityList) {
            ProductsModel product = new ProductsModel();
            product.setId(x.getId());
            product.setName(x.getName());
            product.setImg(x.getImg());
            product.setDescription(x.getDescription());
            productsModelList.add(product);
        }

        return productsModelList;
    }
}
