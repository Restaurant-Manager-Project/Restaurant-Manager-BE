package com.example.Restaurant_Manager_BE.converters.impl;

import com.example.Restaurant_Manager_BE.converters.ConverterDetailsOrder;
import com.example.Restaurant_Manager_BE.converters.ConverterOrder;
import com.example.Restaurant_Manager_BE.dto.OrderDTO;
import com.example.Restaurant_Manager_BE.entities.OrderEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
@RequiredArgsConstructor
public class ConverterOrderImpl implements ConverterOrder {
    private final ConverterDetailsOrder converterDetailsOrder;
    @Override
    public OrderDTO toDTO(OrderEntity entity) {
        if (entity == null) {
            return null;
        }
        return OrderDTO.builder()
                .orderId(entity.getId())
                .dateCreate(entity.getDateCreate())
                .total(entity.getTotal())
                .directionTable(entity.getDirectionTable())
                .tableId(entity.getTable().getId())
                .detailsOrderDTOList(converterDetailsOrder.toDTOList(entity.getDetailsOrderList()))
                .build();
    }

    @Override
    public OrderEntity toEntity(OrderDTO dto) {
        return null;
    }

    @Override
    public List<OrderDTO> toDTOList(List<OrderEntity> entityList) {
        if (entityList == null || entityList.isEmpty()) {
            return null;
        }
        List<OrderDTO> list = new ArrayList<>();
        entityList.forEach(x -> {
            OrderDTO orderDTO = toDTO(x);
            list.add(orderDTO);
        });
        return list;
    }

    @Override
    public List<OrderEntity> toEntityList(List<OrderDTO> dtoList) {
        return null;
    }
}
