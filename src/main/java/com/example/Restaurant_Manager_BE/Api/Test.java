package com.example.Restaurant_Manager_BE.Api;


import com.example.Restaurant_Manager_BE.Model.ProductsModel;
import com.example.Restaurant_Manager_BE.Service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Test {
    @Autowired
    ProductsService productsService;


    @CrossOrigin(origins = "*")
    @GetMapping("/api/products/")
    public List<ProductsModel> test() {
        return productsService.getAll();
    }
    @GetMapping("/api/products")
    public List<ProductsModel> test1(@RequestParam(name="name") String name) {
        return productsService.getByName(name);
    }
}
