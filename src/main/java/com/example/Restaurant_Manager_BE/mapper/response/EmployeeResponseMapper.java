package com.example.Restaurant_Manager_BE.mapper.response;

import com.example.Restaurant_Manager_BE.dto.response.EmployeeResponse;
import com.example.Restaurant_Manager_BE.entities.EmployeeEntity;
import com.example.Restaurant_Manager_BE.mapper.BaseResponseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface EmployeeResponseMapper extends BaseResponseMapper<EmployeeResponse, EmployeeEntity> {

}
