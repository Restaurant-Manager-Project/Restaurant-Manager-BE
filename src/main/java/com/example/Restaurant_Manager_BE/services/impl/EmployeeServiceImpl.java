package com.example.Restaurant_Manager_BE.services.impl;
import java.util.List;

import com.example.Restaurant_Manager_BE.dto.request.EmployeeRequest;
import com.example.Restaurant_Manager_BE.dto.response.EmployeeResponse;
import com.example.Restaurant_Manager_BE.mapper.request.EmployeeRequestMapper;
import com.example.Restaurant_Manager_BE.mapper.response.EmployeeResponseMapper;
import org.springframework.stereotype.Service;
import com.example.Restaurant_Manager_BE.constants.MessageKeys;
import com.example.Restaurant_Manager_BE.entities.EmployeeEntity;
import com.example.Restaurant_Manager_BE.exceptions.DataNotFoundException;
import com.example.Restaurant_Manager_BE.exceptions.InvalidInputException;
import com.example.Restaurant_Manager_BE.repositories.EmployeeRepository;
import com.example.Restaurant_Manager_BE.services.EmployeeService;
import com.example.Restaurant_Manager_BE.utils.LocalizationUtils;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final LocalizationUtils localizationUtils;
    private final EmployeeRequestMapper employeeRequestMapper;
    private final EmployeeResponseMapper employeeResponseMapper;

    @Override
    public EmployeeResponse createEmployee(EmployeeRequest request) {
        if (request == null) {
            throw new InvalidInputException(localizationUtils.getLocalizedMessage(MessageKeys.EMPLOYEE_CREATE_FAILED));
        }
        EmployeeEntity employeeEntity = employeeRequestMapper.toEntity(request);
        EmployeeEntity result = employeeRepository.save(employeeEntity);
        return EmployeeResponse.builder()
                .id(result.getId())
                .firstName(result.getFirstName())
                .lastName(result.getLastName())
                .phone(result.getPhone())
                .address(result.getAddress())
                .gender(result.getGender())
                .build();
    }

    @Override
    public List<EmployeeResponse> getAll() {
        List<EmployeeEntity> employeeEntityList = employeeRepository.findAll();
        List<EmployeeResponse> listResponse = employeeResponseMapper.toListDto(employeeEntityList);
        return listResponse;
    }

    @Override
    public EmployeeResponse getById(Long id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(
                        localizationUtils.getLocalizedMessage(MessageKeys.EMPLOYEE_NOT_EXISTED)));
        EmployeeResponse response = employeeResponseMapper.toDto(employeeEntity);
        return response;
    }

    @Override
    public EmployeeResponse deleteEmployee(Long id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKeys.EMPLOYEE_NOT_EXISTED)));
        employeeEntity.setDeleted(true);
        EmployeeEntity result = employeeRepository.save(employeeEntity);
        return EmployeeResponse.builder()
                .id(result.getId())
                .firstName(result.getFirstName())
                .lastName(result.getLastName())
                .phone(result.getPhone())
                .address(result.getAddress())
                .gender(result.getGender())
                .build();
    }

    @Override
    public EmployeeResponse updateEmployee(Long id, EmployeeRequest request) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(
                        localizationUtils.getLocalizedMessage(MessageKeys.EMPLOYEE_NOT_EXISTED)));
        employeeRequestMapper.update(employeeEntity, request);
        EmployeeEntity result = employeeRepository.save(employeeEntity);
        return EmployeeResponse.builder()
                .id(result.getId())
                .firstName(result.getFirstName())
                .lastName(result.getLastName())
                .phone(result.getPhone())
                .address(result.getAddress())
                .gender(result.getGender())
                .build();
    }

    @Override
    public EmployeeResponse findEmployees(String name) {
//        List<EmployeeEntity> employeeEntityList = employeeRepository.findByFirstNameContainingOrLastNameContaining(name,
//                name);
//        if (employeeEntityList.isEmpty()) {
//            throw new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKeys.EMPLOYEE_NOT_EXISTED));
//        }
//        List<EmployeeDTO> employeeDTOList = converterEmployee.toDTOList(employeeEntityList);
//        APIResponse apiResponse = new APIResponse();
//        apiResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.EMPLOYEE_LIST_GET_SUCCESS));
//        apiResponse.setResult(employeeDTOList);
//        return ResponseEntity.ok(apiResponse);
        return null;
    }

    @Override
    public EmployeeResponse findByUsername(String username) {
        EmployeeEntity employee = employeeRepository.findByAccount_Username(username);
//        return modelMapper.map(employee, EmployeeDTO.class);
        return null;
    }
}
