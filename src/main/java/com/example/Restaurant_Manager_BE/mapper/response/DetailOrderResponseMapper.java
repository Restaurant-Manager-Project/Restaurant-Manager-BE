package com.example.Restaurant_Manager_BE.mapper.response;

import com.example.Restaurant_Manager_BE.dto.response.DetailOrderResponse;
import com.example.Restaurant_Manager_BE.entities.DetailsOrderEntity;
import com.example.Restaurant_Manager_BE.mapper.BaseResponseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DetailOrderResponseMapper extends BaseResponseMapper<DetailOrderResponse, DetailsOrderEntity> {
    @Override
    @Mapping(target = "productId", expression = "java(entity.getProduct().getId())")
    @Mapping(target = "productName", expression = "java(entity.getProduct().getName())")
    @Mapping(target = "orderId", expression = "java(entity.getOrder().getId())")

    DetailOrderResponse toDto(DetailsOrderEntity entity);
}
