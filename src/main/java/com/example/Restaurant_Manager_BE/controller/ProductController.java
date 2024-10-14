package com.example.Restaurant_Manager_BE.controller;


import com.example.Restaurant_Manager_BE.model.ProductsModel;
import com.example.Restaurant_Manager_BE.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ProductController {
    @Autowired
    ProductsService productsService;




    @GetMapping("/api/products/")
    public List<ProductsModel> test() {
        return productsService.getAll();
    }
    @GetMapping("/api/products")
    public List<ProductsModel> test1(@RequestParam Map<String, String> params) {
        if (params.containsKey("name")) {
            return productsService.getByName(params.get("name"));
        }
        else if (params.containsKey("id")) {
            return List.of(productsService.getById(Long.parseLong(params.get("id"))));
        }
        return null;
    }

    @GetMapping("/api/products/details")
    public ProductsModel getDetailsProduct(@RequestParam(name = "id") Long id) {
        return productsService.getById(id);
    }

    @GetMapping("/api/products/123")
    public void test2() {
        productsService.test();
    }
}
