package com.example.Restaurant_Manager_BE.converters;



import com.example.Restaurant_Manager_BE.dto.DetailsOrderDTO;
import com.example.Restaurant_Manager_BE.entities.DetailsOrderEntity;

import java.util.List;

public interface ConverterDetailsOrder {
    public DetailsOrderDTO toDTO(DetailsOrderEntity entity);

    public DetailsOrderEntity toEntity(DetailsOrderDTO dto);

    public List<DetailsOrderDTO> toDTOList(List<DetailsOrderEntity> entityList);

    public List<DetailsOrderEntity> toEntityList(List<DetailsOrderDTO> dtoList);
}
