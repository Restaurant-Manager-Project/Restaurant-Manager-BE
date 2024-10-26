package com.example.Restaurant_Manager_BE.controllers;
import com.example.Restaurant_Manager_BE.constants.MessageKeys;
import com.example.Restaurant_Manager_BE.dto.ProductDTO;
import com.example.Restaurant_Manager_BE.exceptions.DataNotFoundException;
import com.example.Restaurant_Manager_BE.responses.APIResponse;
import com.example.Restaurant_Manager_BE.services.CategoriesService;
import com.example.Restaurant_Manager_BE.utils.LocalizationUtils;
import com.example.Restaurant_Manager_BE.dto.CategoriesDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequiredArgsConstructor
public class CategoriesController {
    private final CategoriesService categoriesService;
    private final LocalizationUtils localizationUtils;
    @Operation(summary = "Thêm loại sản phẩm",description = "Thêm loại của món ăn sau khi nhập đầy đủ thông tin")
    @PostMapping("/api/categories")
    public ResponseEntity<APIResponse> CreateCategories(@RequestBody CategoriesDTO  categoriesDTO){
        if(categoriesDTO == null){
            throw new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKeys.PRODUCT_CREATE_FAILED ));
        }
        return categoriesService.createCategories(categoriesDTO);
    }
}
