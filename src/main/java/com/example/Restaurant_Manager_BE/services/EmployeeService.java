package com.example.Restaurant_Manager_BE.services;

import org.springframework.http.ResponseEntity;

import com.example.Restaurant_Manager_BE.dto.EmployeeDTO;
import com.example.Restaurant_Manager_BE.responses.APIResponse;

public interface EmployeeService {
    ResponseEntity<APIResponse> createEmployee(EmployeeDTO employeeDTO);

    ResponseEntity<APIResponse> getAll();

    ResponseEntity<APIResponse> getById(Long id);

    ResponseEntity<APIResponse> deleteEmployee(Long id);

    ResponseEntity<APIResponse> updateEmployee(Long id, EmployeeDTO employeeDTO);

    ResponseEntity<APIResponse> findEmployees(String name);

    EmployeeDTO findByUsername(String username);

}
