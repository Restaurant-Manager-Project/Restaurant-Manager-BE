package com.example.Restaurant_Manager_BE.controllers;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Restaurant_Manager_BE.responses.APIResponse;
import com.example.Restaurant_Manager_BE.services.SupplierService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SupplierController {
    private final SupplierService supplierService;

    @Operation(summary = "Lấy danh sách nhà cung cấp", description = "Lấy tất cả danh sách nhà cung cấp")
    @GetMapping("/api/suppliers")
    public ResponseEntity<APIResponse> getAllSuppliers() {
        return supplierService.getAll();
    }

    @Operation(summary = "Tìm kiếm nhà cung cấp tiêu chí", description = "Tìm kiếm nhà cung cấp theo tiêu chí {name, address,...}")
    @GetMapping("/api/suppliers/search")
    public ResponseEntity<APIResponse> getSupplierByName(@RequestParam Map<String, String> params) {
        return supplierService.getByName(params.get("name"));
    }

}
