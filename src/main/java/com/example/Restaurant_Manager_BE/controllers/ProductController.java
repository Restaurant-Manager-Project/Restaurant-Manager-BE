package com.example.Restaurant_Manager_BE.controllers;


import com.example.Restaurant_Manager_BE.constants.MessageKeys;
import com.example.Restaurant_Manager_BE.dto.ProductDTO;
import com.example.Restaurant_Manager_BE.exceptions.DataNotFoundException;
import com.example.Restaurant_Manager_BE.responses.APIResponse;
import com.example.Restaurant_Manager_BE.services.ProductService;
import com.example.Restaurant_Manager_BE.utils.LocalizationUtils;
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
    private final LocalizationUtils localizationUtils;

    @Operation(summary = "Lấy danh sách món ăn", description = "Lấy tất cả danh sách món ăn")
    @GetMapping("/api/products")
    public ResponseEntity<APIResponse> getAllProducts() {
        return productService.getAll();
    }

    @Operation(summary = "Tìm kiếm món ăn theo tiêu chí", description = "Tìm kiếm món ăn theo tiêu chí cụ thể {name, price, ...}")

    @GetMapping("/api/products/search")
        public ResponseEntity<APIResponse> findProducts(@RequestParam Map<String, String> params) {
        return productService.getByName(params.get("name"));
    }
    //Phần Create trong CRUD
    @Operation(summary = "Thêm sản phẩm",description = "Thêm món ăn sau khi nhập đầy đủ thông tin")
    @PostMapping("/api/products")
    public ResponseEntity<APIResponse> CreateProduct(@RequestBody ProductDTO ProductDTO){
        if(ProductDTO == null){
            throw new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKeys.PRODUCT_CREATE_FAILED ));
        }
        return productService.createProducts(ProductDTO);
    }
    @Operation(summary = "Xóa sản phẩm",description = "Xóa món ăn theo mã món ăn")
    @DeleteMapping("/api/products/{id}")
    public ResponseEntity<APIResponse> deleteProduct(@PathVariable("id") Long id){
        if(id == null){
            throw new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKeys.PRODUCT_DELETE_FAILED));
        }
        return productService.deleteProducts(id);
    }
    @Operation(summary = "Sửa sản phẩm ",description = "Sửa sản phẩm theo thông tin nhập ")
    @PutMapping("/api/products")
    public ResponseEntity<APIResponse> updateProduct(@RequestBody ProductDTO ProductDTO){
        if(ProductDTO == null){
            throw new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKeys.PRODUCT_UPDATE_FAILED));
        }
        Long id = ProductDTO.getId();
        return productService.updateProducts(id,ProductDTO);
    }
}
    