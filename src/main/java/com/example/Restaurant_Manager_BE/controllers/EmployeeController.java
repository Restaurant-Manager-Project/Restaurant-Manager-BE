package com.example.Restaurant_Manager_BE.controllers;

import com.example.Restaurant_Manager_BE.dto.request.EmployeeRequest;
import com.example.Restaurant_Manager_BE.dto.response.EmployeeResponse;
import com.example.Restaurant_Manager_BE.exceptions.InvalidParamException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Restaurant_Manager_BE.constants.MessageKeys;
import com.example.Restaurant_Manager_BE.responses.APIResponse;
import com.example.Restaurant_Manager_BE.services.EmployeeService;
import com.example.Restaurant_Manager_BE.utils.LocalizationUtils;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;
    private final LocalizationUtils localizationUtils;

    @PreAuthorize("hasAuthority('employee.create')")
    @PostMapping("/api/employees")
    public ResponseEntity<APIResponse> createEmployee(@RequestBody EmployeeRequest request) {
        EmployeeResponse response = employeeService.createEmployee(request);
        APIResponse apiResponse = APIResponse.builder()
                .success(true)
                .message(localizationUtils.getLocalizedMessage(MessageKeys.EMPLOYEE_CREATE_SUCCESS))
                .result(response)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @PreAuthorize("hasAuthority('employee.view')")
    @GetMapping("/api/employees")
    public ResponseEntity<APIResponse> getAllEmployees() {
        List<EmployeeResponse> listResponse = employeeService.getAll();
        APIResponse apiResponse = APIResponse.builder()
                .success(true)
                .message(localizationUtils.getLocalizedMessage(MessageKeys.EMPLOYEE_LIST_GET_SUCCESS))
                .result(listResponse)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @PreAuthorize("hasAuthority('employee.view')")
    @GetMapping("/api/employees/{id}")
    public ResponseEntity<APIResponse> getEmployeeById(@PathVariable("id") Long id) {
        EmployeeResponse response = employeeService.getById(id);
        APIResponse apiResponse = APIResponse.builder()
                .success(true)
                .message(localizationUtils.getLocalizedMessage(MessageKeys.EMPLOYEE_CREATE_SUCCESS))
                .result(response)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @PreAuthorize("hasAuthority('employee.delete')")
    @DeleteMapping("/api/employees/{id}")
    public ResponseEntity<APIResponse> deleteEmployee(@PathVariable Long id) {
        EmployeeResponse response = employeeService.deleteEmployee(id);
        APIResponse apiResponse = APIResponse.builder()
                .success(true)
                .message(localizationUtils.getLocalizedMessage(MessageKeys.EMPLOYEE_CREATE_SUCCESS))
                .result(response)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @PreAuthorize("hasAuthority('employee.update')")
    @PutMapping("/api/employees/{id}")
    public ResponseEntity<APIResponse> updateEmployee(@PathVariable("id") Long id,
            @RequestBody EmployeeRequest employeeRequest) {
        if (employeeRequest == null) {
            throw new InvalidParamException(localizationUtils.getLocalizedMessage(MessageKeys.EMPLOYEE_NOT_EXISTED));
        }
        EmployeeResponse response = employeeService.updateEmployee(id, employeeRequest);
        APIResponse apiResponse = APIResponse.builder()
                .success(true)
                .message(localizationUtils.getLocalizedMessage(MessageKeys.EMPLOYEE_CREATE_SUCCESS))
                .result(response)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

//    @PreAuthorize("hasAuthority('employee.view')")
//    @GetMapping("/api/employees/search")
//    public ResponseEntity<APIResponse> findEmployees(
//            @RequestParam(required = false) String firstName,
//            @RequestParam(required = false) String lastName) {
//
//        if (firstName != null && !firstName.isEmpty()) {
//            return employeeService.findEmployees(firstName);
//
//        } else if (lastName != null && !lastName.isEmpty()) {
//            return employeeService.findEmployees(lastName);
//        } else {
//            return employeeService.getAll();
//        }
//    }

}
