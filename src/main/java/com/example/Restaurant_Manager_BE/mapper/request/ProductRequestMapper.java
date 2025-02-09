package com.example.Restaurant_Manager_BE.mapper.request;

import com.example.Restaurant_Manager_BE.dto.request.ProductRequest;
import com.example.Restaurant_Manager_BE.entities.ProductEntity;
import com.example.Restaurant_Manager_BE.mapper.BaseRequestMapper;
import org.mapstruct.*;



@Mapper(componentModel = "spring")
public interface ProductRequestMapper extends BaseRequestMapper<ProductRequest, ProductEntity> {
    @Mapping(target = "img", ignore = true)
    ProductEntity toEntity(ProductRequest dto);

    @Mapping(target = "img", ignore = true)
    void update(@MappingTarget ProductEntity entit, ProductRequest dto);
}
