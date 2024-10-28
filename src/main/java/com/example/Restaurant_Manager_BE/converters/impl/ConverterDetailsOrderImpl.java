package com.example.Restaurant_Manager_BE.converters.impl;

import com.example.Restaurant_Manager_BE.converters.ConverterDetailsOrder;
import com.example.Restaurant_Manager_BE.dto.DetailsOrderDTO;
import com.example.Restaurant_Manager_BE.entities.DetailsOrderEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConverterDetailsOrderImpl implements ConverterDetailsOrder {
    @Override
    public DetailsOrderDTO toDTO(DetailsOrderEntity entity) {
        if (entity == null) {
            return null;
        }
        return DetailsOrderDTO.builder()
                .id(entity.getId())
                .productId(entity.getProduct().getId())
                .productName(entity.getProduct().getName())
                .quantity(entity.getQuantity())
                .price(entity.getPrice())
                .build();
    }

    @Override
    public DetailsOrderEntity toEntity(DetailsOrderDTO dto) {
        return null;
    }

    @Override
    public List<DetailsOrderDTO> toDTOList(List<DetailsOrderEntity> entityList) {
        if (entityList == null || entityList.isEmpty()) {
            return null;
        }
        List<DetailsOrderDTO> list = new ArrayList<>();
        entityList.forEach(x -> {
            DetailsOrderDTO detailsOrderDTO = toDTO(x);
            list.add(detailsOrderDTO);
        });
        return list;
    }

    @Override
    public List<DetailsOrderEntity> toEntityList(List<DetailsOrderDTO> dtoList) {
        return null;
    }
}
