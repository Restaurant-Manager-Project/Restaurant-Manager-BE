package com.example.Restaurant_Manager_BE.converters.impl;

import com.example.Restaurant_Manager_BE.converters.ConverterDetailsOrder;
import com.example.Restaurant_Manager_BE.converters.ConverterOrder;
import com.example.Restaurant_Manager_BE.dto.OrderDTO;
import com.example.Restaurant_Manager_BE.entities.OrderEntity;
import com.example.Restaurant_Manager_BE.entities.TableEntity;
import com.example.Restaurant_Manager_BE.repositories.TableRepository;
import com.example.Restaurant_Manager_BE.services.TableService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
@RequiredArgsConstructor
public class ConverterOrderImpl implements ConverterOrder {
    private final ConverterDetailsOrder converterDetailsOrder;
    private final TableRepository tableRepository;
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
        if (dto == null) {
            return null;
        }
        TableEntity tableEntity = tableRepository.findById(dto.getTableId()).get();
        OrderEntity orderEntity = OrderEntity.builder()
            .dateCreate(dto.getDateCreate())
            .total(dto.getTotal())
            .table(tableEntity)
            .directionTable(dto.getDirectionTable())
            .isDeleted(false)
            .build();
        orderEntity.setDetailsOrderList(converterDetailsOrder.toEntityList(dto.getDetailsOrderDTOList(), orderEntity));
        return orderEntity;
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
        if (dtoList == null || dtoList.isEmpty()) {
            return null;
        }
        List<OrderEntity> list = new ArrayList<>();
        dtoList.forEach(x -> {
            OrderEntity orderEntity = toEntity(x);
            list.add(orderEntity);
        });
        return list;
    }
}
