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
import com.example.Restaurant_Manager_BE.dto.SupplierDTO;
import com.example.Restaurant_Manager_BE.exceptions.DataNotFoundException;
import com.example.Restaurant_Manager_BE.responses.APIResponse;
import com.example.Restaurant_Manager_BE.services.SupplierService;
import com.example.Restaurant_Manager_BE.utils.LocalizationUtils;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequiredArgsConstructor
public class SupplierController {
    private final SupplierService supplierService;
    private final LocalizationUtils localizationUtils;

    @PreAuthorize("hasAuthority('supplier.view')")
    @Operation(summary = "Lấy danh sách nhà cung cấp", description = "Lấy tất cả danh sách nhà cung cấp")
    @GetMapping("/api/suppliers")
    public ResponseEntity<APIResponse> getAllSuppliers() {
        return supplierService.getAll();
    }

    @PreAuthorize("hasAuthority('supplier.view')")
    @Operation(summary = "Tìm kiếm nhà cung cấp tiêu chí", description = "Tìm kiếm nhà cung cấp theo tiêu chí {name, address,...}")
    @GetMapping("/api/suppliers/search")
    public ResponseEntity<APIResponse> getSupplierByName(@RequestParam Map<String, String> params) {
        return supplierService.getByName(params.get("name"));
    }

    @PreAuthorize("hasAuthority('supplier.view')")
    @Operation(summary = "Tìm kiếm nhà cung cấp theo id")
    @GetMapping("/api/supplier/{id}")
    public ResponseEntity<APIResponse> getSupplierById(@PathVariable("id") Long id) {
        return supplierService.getById(id);
    }

    @PreAuthorize("hasAuthority('supplier.create')")
    @Operation(summary = "Tạo nhà cung cấp mới")
    @PostMapping("/api/suppliers")
    public ResponseEntity<APIResponse> createSupplier(@RequestBody SupplierDTO supplierDTO) {
        if (supplierDTO == null) {
            throw new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKeys.SUPPLIER_NOT_EXISTED));
        }
        return supplierService.createSupplier(supplierDTO);
    }

    @PreAuthorize("hasAuthority('supplier.delete')")
    @Operation(summary = "Xóa nhà cung cấp")
    @DeleteMapping("/api/supplier/{id}")
    public ResponseEntity<APIResponse> deleteSupplier(@PathVariable("id") Long supplierId) {
        return supplierService.deleteSupplier(supplierId);
    }

    @Operation(summary = "Chỉnh sửa thông tin nhà cung cấp")
    @PutMapping("/api/supplier")
    @PreAuthorize("hasAuthority('supplier.update')")
    public ResponseEntity<APIResponse> updateSupplier(@RequestBody SupplierDTO supplierDTO) {
        if (supplierDTO == null) {
            throw new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKeys.SUPPLIER_NOT_EXISTED));
        }
        return supplierService.updateSupplier(supplierDTO);
    }
}
