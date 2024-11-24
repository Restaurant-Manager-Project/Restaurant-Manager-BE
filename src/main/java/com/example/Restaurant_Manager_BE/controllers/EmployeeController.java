package com.example.Restaurant_Manager_BE.controllers;

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
import com.example.Restaurant_Manager_BE.dto.EmployeeDTO;
import com.example.Restaurant_Manager_BE.exceptions.DataNotFoundException;
import com.example.Restaurant_Manager_BE.responses.APIResponse;
import com.example.Restaurant_Manager_BE.services.EmployeeService;
import com.example.Restaurant_Manager_BE.utils.LocalizationUtils;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;
    private final LocalizationUtils localizationUtils;

    @PreAuthorize("hasRole('employee.create')")
    @PostMapping("/api/employees")
    public ResponseEntity<APIResponse> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return employeeService.createEmployee(employeeDTO);
    }

    @PreAuthorize("hasRole('employee.view')")
    @GetMapping("/api/employees")
    public ResponseEntity<APIResponse> getAllEmployees() {
        return employeeService.getAll();
    }

    @PreAuthorize("hasRole('employee.view')")
    @GetMapping("/api/employees/{id}")
    public ResponseEntity<APIResponse> getEmployeeById(@PathVariable("id") Long id) {
        return employeeService.getById(id);
    }

    @PreAuthorize("hasRole('employee.delete')")
    @DeleteMapping("/api/employees/{id}")
    public ResponseEntity<APIResponse> deleteEmployee(@PathVariable Long id) {
        return employeeService.deleteEmployee(id);
    }

    @PreAuthorize("hasRole('employee.update')")
    @PutMapping("/api/employees/{id}")
    public ResponseEntity<APIResponse> updateEmployee(@PathVariable("id") Long id,
            @RequestBody EmployeeDTO employeeDTO) {
        if (employeeDTO == null) {
            throw new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKeys.EMPLOYEE_NOT_EXISTED));
        }
        return employeeService.updateEmployee(id, employeeDTO);
    }

    @PreAuthorize("hasRole('employee.view')")
    @GetMapping("/api/employees/search")
    public ResponseEntity<APIResponse> findEmployees(
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName) {

        if (firstName != null && !firstName.isEmpty()) {
            return employeeService.findEmployees(firstName);

        } else if (lastName != null && !lastName.isEmpty()) {
            return employeeService.findEmployees(lastName);
        } else {
            return employeeService.getAll();
        }
    }

}
