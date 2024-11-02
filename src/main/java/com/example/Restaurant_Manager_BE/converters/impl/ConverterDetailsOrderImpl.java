package com.example.Restaurant_Manager_BE.converters.impl;

import com.example.Restaurant_Manager_BE.converters.ConverterDetailsOrder;
import com.example.Restaurant_Manager_BE.dto.DetailsOrderDTO;
import com.example.Restaurant_Manager_BE.entities.DetailsOrderEntity;
import com.example.Restaurant_Manager_BE.entities.OrderEntity;
import com.example.Restaurant_Manager_BE.repositories.OrderRepository;
import com.example.Restaurant_Manager_BE.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ConverterDetailsOrderImpl implements ConverterDetailsOrder {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
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
        if (dto == null) {
            return null;
        }

        return DetailsOrderEntity.builder()
                .id(dto.getId())
                .quantity(dto.getQuantity())
                .product(productRepository.findById(dto.getProductId()).get())
                .price(dto.getPrice())
                .isDeleted(false)
                .build();
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
    public List<DetailsOrderEntity> toEntityList(List<DetailsOrderDTO> dtoList, OrderEntity orderEntity) {
        if (dtoList == null || dtoList.isEmpty()) {
            return null;
        }
        List<DetailsOrderEntity> list = new ArrayList<>();
        dtoList.forEach(x -> {
            DetailsOrderEntity detailsOrderEntity = toEntity(x);
            detailsOrderEntity.setOrder(orderEntity);
            list.add(detailsOrderEntity);
        });
        return list;
    }
}
