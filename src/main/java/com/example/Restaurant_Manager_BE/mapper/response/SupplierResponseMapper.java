package com.example.Restaurant_Manager_BE.mapper.response;

import com.example.Restaurant_Manager_BE.dto.response.SupplierResponse;
import com.example.Restaurant_Manager_BE.entities.SupplierEntity;
import com.example.Restaurant_Manager_BE.mapper.BaseResponseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SupplierResponseMapper extends BaseResponseMapper<SupplierResponse, SupplierEntity> {
}
