package com.example.Restaurant_Manager_BE.converters;

import com.example.Restaurant_Manager_BE.dto.StatisticDTO.ProductStatisticDTO;
import com.example.Restaurant_Manager_BE.entities.ProductEntity;

import java.util.List;

public interface ConverterStatistic {
    public ProductStatisticDTO toProductStatisticDTO(Object[] query_result);
    public List<ProductStatisticDTO> ProductStatisticDTO_List(List<Object[]> results);
}
