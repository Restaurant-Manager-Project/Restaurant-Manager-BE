package com.example.Restaurant_Manager_BE.services;

import com.example.Restaurant_Manager_BE.dto.request.SupplierRequest;
import com.example.Restaurant_Manager_BE.dto.response.SupplierResponse;

import java.util.List;

public interface SupplierService {
    List<SupplierResponse> getAll();

    List<SupplierResponse> getByName(String name);

    SupplierResponse getById(Long id);

    boolean createSupplier(SupplierRequest supplierRequest);

    boolean deleteSupplier(Long supplierId);

    boolean updateSupplier(Long id, SupplierRequest supplierRequest);
}
