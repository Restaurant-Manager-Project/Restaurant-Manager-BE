package com.example.Restaurant_Manager_BE.mapper.request;

import com.example.Restaurant_Manager_BE.dto.request.SupplierRequest;
import com.example.Restaurant_Manager_BE.entities.SupplierEntity;
import com.example.Restaurant_Manager_BE.mapper.BaseRequestMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SupplierRequestMapper extends BaseRequestMapper<SupplierRequest, SupplierEntity> {
}
