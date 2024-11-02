package com.example.Restaurant_Manager_BE.converters;

import com.example.Restaurant_Manager_BE.dto.OrderDTO;
import com.example.Restaurant_Manager_BE.entities.OrderEntity;

import java.util.List;

public interface ConverterOrder {
    public OrderDTO toDTO(OrderEntity entity);

    public OrderEntity toEntity(OrderDTO dto);

    public List<OrderDTO> toDTOList(List<OrderEntity> entityList);

    public List<OrderEntity> toEntityList(List<OrderDTO> dtoList);
}
