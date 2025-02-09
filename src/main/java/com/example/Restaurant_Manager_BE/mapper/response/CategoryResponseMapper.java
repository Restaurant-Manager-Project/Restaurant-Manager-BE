package com.example.Restaurant_Manager_BE.mapper.response;

import com.example.Restaurant_Manager_BE.dto.response.CategoryResponse;
import com.example.Restaurant_Manager_BE.entities.CategoryEntity;
import com.example.Restaurant_Manager_BE.mapper.BaseResponseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryResponseMapper extends BaseResponseMapper<CategoryResponse, CategoryEntity> {

}
