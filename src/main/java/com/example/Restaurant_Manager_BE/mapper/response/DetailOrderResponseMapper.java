package com.example.Restaurant_Manager_BE.mapper.response;

import com.example.Restaurant_Manager_BE.dto.response.DetailOrderResponse;
import com.example.Restaurant_Manager_BE.entities.DetailsOrderEntity;
import com.example.Restaurant_Manager_BE.mapper.BaseResponseMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DetailOrderResponseMapper extends BaseResponseMapper<DetailOrderResponse, DetailsOrderEntity> {
}
