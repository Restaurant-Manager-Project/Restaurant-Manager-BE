package com.example.Restaurant_Manager_BE.Api;


import com.example.Restaurant_Manager_BE.Model.ProductsModel;
import com.example.Restaurant_Manager_BE.Service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Test {
    @Autowired
    ProductsService productsService;

    @GetMapping("/api/products/")
    public List<ProductsModel> test() {
        return productsService.getAll();
    }
}
