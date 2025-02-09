package com.example.Restaurant_Manager_BE.mapper.request;

import com.example.Restaurant_Manager_BE.dto.request.ImportRequest;
import com.example.Restaurant_Manager_BE.entities.ImportEntity;
import com.example.Restaurant_Manager_BE.mapper.BaseRequestMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface ImportRequestMapper extends BaseRequestMapper<ImportRequest, ImportEntity> {
}
