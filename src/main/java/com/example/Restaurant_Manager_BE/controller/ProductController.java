package com.example.Restaurant_Manager_BE.controller;


import com.example.Restaurant_Manager_BE.model.DetailsProductModel;
import com.example.Restaurant_Manager_BE.model.ProductsModel;
import com.example.Restaurant_Manager_BE.service.ProductsService;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ProductController {
    @Autowired
    ProductsService productsService;


    @Operation(summary = "Lấy danh sách món ăn", description = "Lấy tất cả danh sách món ăn")
    @GetMapping("/api/products")
    public List<ProductsModel> getAllProducts() {
        return productsService.getAll();
    }

    @Operation(summary = "Tìm kiếm món ăn theo tiêu chí", description = "Tìm kiếm món ăn theo tiêu chí cụ thể {name, price, ...}")

    @GetMapping("/api/products/search")
        public List<ProductsModel> findProducts(@RequestParam Map<String, String> params) {
        if (params.containsKey("name")) {
            return productsService.getByName(params.get("name"));
        } else if (params.containsKey("id")) {
            return List.of(productsService.getById(Long.parseLong(params.get("id"))));
        }
        return null;
    }

    @Operation(summary = "Tìm kiếm", description = "Tìm kiếm món ăn theo {id}")
    @GetMapping("/api/products/{id}")
    public ProductsModel getDetailsProduct(@PathVariable Long id) {
        return productsService.getById(id);
    }

}
