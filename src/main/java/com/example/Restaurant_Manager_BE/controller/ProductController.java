package com.example.Restaurant_Manager_BE.controller;


import com.example.Restaurant_Manager_BE.exception.MessageResponse;
import com.example.Restaurant_Manager_BE.model.DetailsProductModel;
import com.example.Restaurant_Manager_BE.model.OrderModel;
import com.example.Restaurant_Manager_BE.model.ProductsModel;
import com.example.Restaurant_Manager_BE.service.ProductsService;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<MessageResponse> getAllProducts() {
        MessageResponse messageResponse = new MessageResponse();

        messageResponse.setResult(productsService.getAll());
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

    @Operation(summary = "Tìm kiếm món ăn theo tiêu chí", description = "Tìm kiếm món ăn theo tiêu chí cụ thể {name, price, ...}")

    @GetMapping("/api/products/search")
        public ResponseEntity<MessageResponse> findProducts(@RequestParam Map<String, String> params) {
        MessageResponse messageResponse = new MessageResponse();
        if (params.containsKey("name")) {
            messageResponse.setResult(productsService.getByName(params.get("name")));
        } else if (params.containsKey("id")) {
            messageResponse.setResult(productsService.getById(Long.parseLong(params.get("id"))));
        }
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

    @Operation(summary = "Tìm kiếm", description = "Tìm kiếm món ăn theo {id}")
    @GetMapping("/api/products/{id}")
    public ResponseEntity<MessageResponse> getDetailsProduct(@PathVariable Long id) {
        MessageResponse messageResponse = new MessageResponse();
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }

}
