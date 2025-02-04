package com.example.Restaurant_Manager_BE.mapper;

import org.mapstruct.Mapper;

import com.example.Restaurant_Manager_BE.dto.request.CategoryRequest;
import com.example.Restaurant_Manager_BE.dto.response.CategoryResponse;
import com.example.Restaurant_Manager_BE.entities.CategoryEntity;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends BaseMapper<CategoryRequest,CategoryResponse,CategoryEntity>{
    
}
