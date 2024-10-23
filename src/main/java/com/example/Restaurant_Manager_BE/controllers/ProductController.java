package com.example.Restaurant_Manager_BE.controllers;


import com.example.Restaurant_Manager_BE.responses.APIResponse;
import com.example.Restaurant_Manager_BE.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;


    @Operation(summary = "Lấy danh sách món ăn", description = "Lấy tất cả danh sách món ăn")
    @GetMapping("/api/products")
    public ResponseEntity<APIResponse> getAllProducts() {
        APIResponse APIResponse = new APIResponse();

        APIResponse.setResult(productService.getAll());
        return new ResponseEntity<>(APIResponse, HttpStatus.OK);
    }

    @Operation(summary = "Tìm kiếm món ăn theo tiêu chí", description = "Tìm kiếm món ăn theo tiêu chí cụ thể {name, price, ...}")

    @GetMapping("/api/products/search")
        public ResponseEntity<APIResponse> findProducts(@RequestParam Map<String, String> params) {
        APIResponse APIResponse = new APIResponse();
        if (params.containsKey("name")) {
            APIResponse.setResult(productService.getByName(params.get("name")));
        } else if (params.containsKey("id")) {
            APIResponse.setResult(productService.getById(Long.parseLong(params.get("id"))));
        }
        return new ResponseEntity<>(APIResponse, HttpStatus.OK);
    }

    @Operation(summary = "Tìm kiếm", description = "Tìm kiếm món ăn theo {id}")
    @GetMapping("/api/products/{id}")
    public ResponseEntity<APIResponse> getDetailsProduct(@PathVariable Long id) {
        APIResponse APIResponse = new APIResponse();
        return new ResponseEntity<>(APIResponse, HttpStatus.OK);
    }

}
