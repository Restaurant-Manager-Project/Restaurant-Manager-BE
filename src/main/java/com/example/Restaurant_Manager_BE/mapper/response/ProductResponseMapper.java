package com.example.Restaurant_Manager_BE.mapper.response;

import com.example.Restaurant_Manager_BE.dto.response.ProductRespose;
import com.example.Restaurant_Manager_BE.entities.ProductEntity;
import com.example.Restaurant_Manager_BE.mapper.BaseResponseMapper;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ProductResponseMapper extends BaseResponseMapper<ProductRespose, ProductEntity> {

}
