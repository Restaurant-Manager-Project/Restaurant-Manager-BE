package com.example.Restaurant_Manager_BE.controllers;

import com.example.Restaurant_Manager_BE.constants.MessageKeys;
import com.example.Restaurant_Manager_BE.dto.ProductDTO;
import com.example.Restaurant_Manager_BE.exceptions.DataNotFoundException;
import com.example.Restaurant_Manager_BE.repositories.ProductRepository;
import com.example.Restaurant_Manager_BE.responses.APIResponse;
import com.example.Restaurant_Manager_BE.services.ProductService;
import com.example.Restaurant_Manager_BE.utils.LocalizationUtils;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class StatisticController {
    private final ProductService productService;
    private final LocalizationUtils localizationUtils;

    @Operation(summary = "Thống kê sản phẩm theo số lượng bán được và xếp hạng chúng")
    @GetMapping("api/statistic/products/{category_id}")
    public ResponseEntity<APIResponse> getStatisticProductsByCategory(@PathVariable("category_id")
                                                                          Long category_id) {
        return productService.StatisticProductByCategoryAndSoldQuantity(category_id);
    }
}
