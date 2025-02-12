package com.example.Restaurant_Manager_BE.mapper.request;

import com.example.Restaurant_Manager_BE.dto.request.CategoryRequest;
import com.example.Restaurant_Manager_BE.dto.request.ProductRequest;
import com.example.Restaurant_Manager_BE.entities.CategoryEntity;
import com.example.Restaurant_Manager_BE.entities.ProductEntity;
import com.example.Restaurant_Manager_BE.mapper.BaseRequestMapper;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface CategoryRequestMapper extends BaseRequestMapper<CategoryRequest, CategoryEntity> {

    @Mapping(target = "img", ignore = true)
    CategoryEntity toEntity(CategoryRequest dto);

    @Mapping(target = "img", ignore = true)
    void update(@MappingTarget CategoryEntity entity, CategoryRequest dto);
}
