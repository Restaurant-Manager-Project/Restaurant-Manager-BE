package com.example.Restaurant_Manager_BE.services.impl;
import java.util.List;
import java.util.Optional;
import com.example.Restaurant_Manager_BE.converters.ConverterEmployee;
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
    private final ConverterEmployee converterEmployee;
    @Override
    public ResponseEntity<APIResponse> createEmployee(EmployeeDTO employeeDTO) {
        if (employeeDTO == null) {
            throw new InvalidInputException(localizationUtils.getLocalizedMessage(MessageKeys.EMPLOYEE_CREATE_FAILED));
        }
        EmployeeEntity employeeEntity = converterEmployee.toEntity(employeeDTO);
//        employeeEntity.setIsDeleted(false);
        employeeRepository.save(employeeEntity);
        APIResponse apiResponse = new APIResponse();
        apiResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.EMPLOYEE_CREATE_SUCCESS));
        return ResponseEntity.ok(apiResponse);
    }

    @Override
    public ResponseEntity<APIResponse> getAll() {
        List<EmployeeEntity> employeeEntityList = employeeRepository.findAll();
        List<EmployeeDTO> employeeDTOList = converterEmployee.toDTOList(employeeEntityList);
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
        EmployeeDTO employeeDTO =converterEmployee.toDTO(employeeEntity);
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
        EmployeeEntity employeeEntity_update=converterEmployee.toEntity(employeeDTO);
        converterEmployee.mergeNonNullFields(employeeEntity, employeeEntity_update);
        employeeRepository.save(employeeEntity);
        APIResponse APIResponse = new APIResponse();
        APIResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.EMPLOYEE_UPDATE_SUCCESS));
        return ResponseEntity.status(HttpStatus.OK).body(APIResponse);
    }

    @Override
    public ResponseEntity<APIResponse> findEmployees(String name) {
        List<EmployeeEntity> employeeEntityList = employeeRepository.findByFirstNameContainingOrLastNameContaining(name,
                name);
        if (employeeEntityList.isEmpty()) {
            throw new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKeys.EMPLOYEE_NOT_EXISTED));
        }
        List<EmployeeDTO> employeeDTOList = converterEmployee.toDTOList(employeeEntityList);
        APIResponse apiResponse = new APIResponse();
        apiResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.EMPLOYEE_LIST_GET_SUCCESS));
        apiResponse.setResult(employeeDTOList);
        return ResponseEntity.ok(apiResponse);
    }

}
