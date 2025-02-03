package com.example.Restaurant_Manager_BE.services;

import com.example.Restaurant_Manager_BE.dto.request.EmployeeRequest;
import com.example.Restaurant_Manager_BE.dto.response.EmployeeResponse;

import java.util.List;

public interface EmployeeService {
    EmployeeResponse createEmployee(EmployeeRequest request);

    List<EmployeeResponse> getAll();

    EmployeeResponse getById(Long id);

    EmployeeResponse deleteEmployee(Long id);

    EmployeeResponse updateEmployee(Long id, EmployeeRequest request);

    EmployeeResponse findEmployees(String name);

    EmployeeResponse findByUsername(String username);

}
