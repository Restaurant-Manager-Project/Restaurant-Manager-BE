package com.example.Restaurant_Manager_BE.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Restaurant_Manager_BE.constants.MessageKeys;
import com.example.Restaurant_Manager_BE.dto.EmployeeDTO;
import com.example.Restaurant_Manager_BE.entities.EmployeeEntity;
import com.example.Restaurant_Manager_BE.exceptions.DataNotFoundException;
import com.example.Restaurant_Manager_BE.exceptions.InvalidInputException;
import com.example.Restaurant_Manager_BE.repositories.EmployeeRepository;
import com.example.Restaurant_Manager_BE.responses.APIResponse;
import com.example.Restaurant_Manager_BE.services.EmployeeService;
import com.example.Restaurant_Manager_BE.utils.LocalizationUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final LocalizationUtils localizationUtils;
    private final ModelMapper modelMapper;

    @Override
    public ResponseEntity<APIResponse> createEmployee(EmployeeDTO employeeDTO) {

        EmployeeEntity employeeEntity = modelMapper.map(employeeDTO, EmployeeEntity.class);
        employeeEntity.setIsDeleted(false);
        if (employeeRepository.save(employeeEntity) == null) {
            throw new InvalidInputException(localizationUtils.getLocalizedMessage(MessageKeys.EMPLOYEE_CREATE_FAILED));
        }
        APIResponse apiResponse = new APIResponse();
        apiResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.EMPLOYEE_CREATE_SUCCESS));
        return ResponseEntity.ok(apiResponse);
    }

    @Override
    public ResponseEntity<APIResponse> getAll() {
        List<EmployeeEntity> employeeEntityList = employeeRepository.findAll();
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        employeeEntityList.forEach(employeeEntity -> {
            EmployeeDTO employee = modelMapper.map(employeeEntity, EmployeeDTO.class);
            employeeDTOList.add(employee);
        });
        APIResponse apiResponse = new APIResponse();
        apiResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.EMPLOYEE_LIST_GET_SUCCESS));
        apiResponse.setResult(employeeDTOList);
        return ResponseEntity.ok(apiResponse);
    }

    @Override
    public ResponseEntity<APIResponse> getById(Long id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(
                        localizationUtils.getLocalizedMessage(MessageKeys.EMPLOYEE_NOT_EXISTED)));

        EmployeeDTO employeeDTO = modelMapper.map(employeeEntity, EmployeeDTO.class);

        APIResponse apiResponse = new APIResponse();
        apiResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.EMPLOYEE_GET_SUCCESS));
        apiResponse.setResult(employeeDTO);
        return ResponseEntity.ok(apiResponse);
    }

    @Override
    public ResponseEntity<APIResponse> deleteEmployee(Long id) {
        Optional<EmployeeEntity> emOptional = employeeRepository.findById(id);

        if (emOptional.isPresent()) {
            EmployeeEntity employeeEntity = emOptional.get();
            employeeEntity.setIsDeleted(true);
            employeeRepository.save(employeeEntity);
            APIResponse apiResponse = new APIResponse();
            apiResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.EMPLOYEE_DELETE_SUCCESS));
            apiResponse.setResult(employeeEntity);
            return ResponseEntity.ok(apiResponse);
        } else {
            APIResponse apiResponse = new APIResponse();
            apiResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.EMPLOYEE_DELETE_FAILED));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }
    }

    @Override
    public ResponseEntity<APIResponse> updateEmployee(Long id, EmployeeDTO employeeDTO) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(
                        localizationUtils.getLocalizedMessage(MessageKeys.EMPLOYEE_NOT_EXISTED)));
        modelMapper.typeMap(EmployeeDTO.class, EmployeeEntity.class)
                .addMappings(mapper -> mapper.skip(EmployeeEntity::setId));
        modelMapper.map(employeeDTO, employeeEntity);
        employeeRepository.save(employeeEntity);
        APIResponse APIResponse = new APIResponse();
        APIResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.CATEGORY_UPDATE_SUCCESS));
        return ResponseEntity.status(HttpStatus.OK).body(APIResponse);
    }

    @Override
    public ResponseEntity<APIResponse> findEmployees(String name) {
        List<EmployeeEntity> employeeEntityList = employeeRepository.findByFirstNameContainingOrLastNameContaining(name,
                name);
        if (employeeEntityList.isEmpty()) {
            throw new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKeys.EMPLOYEE_NOT_EXISTED));
        }

        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        employeeEntityList.forEach(employeeEntity -> {
            EmployeeDTO employeeDTO = modelMapper.map(employeeEntity, EmployeeDTO.class);
            employeeDTOList.add(employeeDTO);
        });

        APIResponse apiResponse = new APIResponse();
        apiResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.EMPLOYEE_LIST_GET_SUCCESS));
        apiResponse.setResult(employeeDTOList);
        return ResponseEntity.ok(apiResponse);
    }

}
