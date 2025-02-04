package com.example.Restaurant_Manager_BE.mapper.request;

import com.example.Restaurant_Manager_BE.dto.request.EmployeeRequest;
import com.example.Restaurant_Manager_BE.dto.response.EmployeeResponse;
import com.example.Restaurant_Manager_BE.entities.EmployeeEntity;
import com.example.Restaurant_Manager_BE.mapper.BaseRequestMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeRequestMapper extends BaseRequestMapper<EmployeeRequest, EmployeeEntity> {

}
