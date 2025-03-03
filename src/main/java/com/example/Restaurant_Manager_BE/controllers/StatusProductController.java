package com.example.Restaurant_Manager_BE.controllers;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Restaurant_Manager_BE.constants.MessageKeys;
import com.example.Restaurant_Manager_BE.dto.StatusProductDTO;
import com.example.Restaurant_Manager_BE.exceptions.DataNotFoundException;
import com.example.Restaurant_Manager_BE.responses.APIResponse;
import com.example.Restaurant_Manager_BE.utils.LocalizationUtils;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class StatusProductController {
    private final StatusProductService statusProductService;
    private final LocalizationUtils localizationUtils;

    @Operation(summary = "Tìm kiếm trạng thái món ăn")
    @GetMapping("/api/status-products/search")
    public ResponseEntity<APIResponse> getByName(@RequestParam Map<String, String> params) {
        return statusProductService.getByName(params.get("name"));
    }

    @Operation(summary = "Tìm kiếm trạng thái món ăn theo id")
    @GetMapping("/api/status-product/{id}")
    public ResponseEntity<APIResponse> getById(@PathVariable("id") Long id) {
        return statusProductService.getById(id);
    }

    @Operation(summary = "Tạo trạng thái món ăn mới")
    @PostMapping("/api/status-products")
    public ResponseEntity<APIResponse> createStatusProduct(@RequestBody StatusProductDTO statusProductDTO) {

        return statusProductService.createStatusProduct(statusProductDTO);
    }

    @Operation(summary = "Xóa trạng thái món ăn")
    @DeleteMapping("/api/status-product/{id}")
    public ResponseEntity<APIResponse> deleteStatusProduct(@PathVariable("id") Long id) {
        return statusProductService.deleteStatusProduct(id);
    }

    @Operation(summary = "Chỉnh sửa trạng thái món ăn")
    @PutMapping("/api/status-product")

    public ResponseEntity<APIResponse> updateStatusProduct(@RequestBody StatusProductDTO statusProductDTO) {
        if (statusProductDTO == null) {
            throw new DataNotFoundException(
                    localizationUtils.getLocalizedMessage(MessageKeys.STATUS_PRODUCT_NOT_EXISTED));
        }
        return statusProductService.updateStatusProduct(statusProductDTO);
    }
}
