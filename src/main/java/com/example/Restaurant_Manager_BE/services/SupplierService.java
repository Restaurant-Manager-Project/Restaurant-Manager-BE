package com.example.Restaurant_Manager_BE.services;

import com.example.Restaurant_Manager_BE.dto.request.SupplierRequest;
import com.example.Restaurant_Manager_BE.dto.response.SupplierResponse;
import org.springframework.http.ResponseEntity;

import com.example.Restaurant_Manager_BE.dto.SupplierDTO;
import com.example.Restaurant_Manager_BE.responses.APIResponse;

import io.micrometer.core.ipc.http.HttpSender.Response;

import java.util.List;

public interface SupplierService {
    List<SupplierResponse> getAll();

    List<SupplierResponse> getByName(String name);

    SupplierResponse getById(Long id);

    boolean createSupplier(SupplierRequest supplierRequest);

    boolean deleteSupplier(Long supplierId);

    boolean updateSupplier(Long id, SupplierRequest supplierRequest);
}
