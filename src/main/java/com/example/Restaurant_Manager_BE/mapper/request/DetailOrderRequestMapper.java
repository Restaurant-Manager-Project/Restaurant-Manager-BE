package com.example.Restaurant_Manager_BE.mapper.request;


import com.example.Restaurant_Manager_BE.dto.request.DetailOrderRequest;
import com.example.Restaurant_Manager_BE.entities.DetailsOrderEntity;
import com.example.Restaurant_Manager_BE.mapper.BaseRequestMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DetailOrderRequestMapper extends BaseRequestMapper<DetailOrderRequest, DetailsOrderEntity> {
}
