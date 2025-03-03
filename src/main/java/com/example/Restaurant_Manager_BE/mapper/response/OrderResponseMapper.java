package com.example.Restaurant_Manager_BE.mapper.response;

import com.example.Restaurant_Manager_BE.dto.response.OrderResponse;
import com.example.Restaurant_Manager_BE.entities.OrderEntity;
import com.example.Restaurant_Manager_BE.mapper.BaseResponseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring",
        uses = DetailOrderResponseMapper.class)
public interface OrderResponseMapper extends BaseResponseMapper<OrderResponse, OrderEntity> {

    @Override
    @Mapping(target = "tableId", expression = "java(entity.getTable().getId())")
    @Mapping(target = "nameTable", expression = "java(entity.getTable().getName())")
    @Mapping(target = "processName", expression = "java(entity.getProcess().getDesc())")
    @Mapping(target = "dateCreate", source = "dateCreate")
    @Mapping(target = "detailsOrderList", source = "entity.detailsOrderList")
    OrderResponse toDto(OrderEntity entity);
}
