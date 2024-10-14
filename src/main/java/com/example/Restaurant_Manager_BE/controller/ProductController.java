package com.example.Restaurant_Manager_BE.controller;


import com.example.Restaurant_Manager_BE.model.ProductsModel;
import com.example.Restaurant_Manager_BE.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    ProductsService productsService;




    @GetMapping("/api/products/")
    public List<ProductsModel> test() {
        return productsService.getAll();
    }
    @GetMapping("/api/products")
    public List<ProductsModel> test1(@RequestParam(name="name") String name) {
        return productsService.getByName(name);
    }

    @GetMapping("/api/products/123")
    public void test2() {
        productsService.test();
    }
}
