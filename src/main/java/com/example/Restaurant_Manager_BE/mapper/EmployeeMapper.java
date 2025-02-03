package com.example.Restaurant_Manager_BE.mapper;

import com.example.Restaurant_Manager_BE.dto.request.EmployeeRequest;
import com.example.Restaurant_Manager_BE.dto.response.EmployeeResponse;
import com.example.Restaurant_Manager_BE.entities.EmployeeEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper extends BaseMapper<EmployeeRequest, EmployeeResponse, EmployeeEntity> {

}
