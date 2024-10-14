package com.example.Restaurant_Manager_BE.service.impl;

import com.example.Restaurant_Manager_BE.entity.ProductEntity;
import com.example.Restaurant_Manager_BE.model.ProductsModel;
import com.example.Restaurant_Manager_BE.repository.ProductsRepository;
import com.example.Restaurant_Manager_BE.service.ProductsService;
import org.modelmapper.ModelMapper;
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
            product.setCategory_name(x.getCategory().getName());
            product.setPrice(x.getPrice());
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
            product.setPrice(x.getPrice());
            productsModelList.add(product);
        }

        return productsModelList;
    }

    @Override
    public ProductsModel getById(Long id) {
        ProductEntity productEntity = productsRepository.findById(id).get();
        ModelMapper modelMapper = new ModelMapper();
        ProductsModel product = modelMapper.map(productEntity, ProductsModel.class);
        product.setCategory_name(productEntity.getCategory().getName());
        return product;
    }
    public void test() {
        List<ProductEntity> list = productsRepository.getProduct();
        System.out.println("aa");
    }
}
