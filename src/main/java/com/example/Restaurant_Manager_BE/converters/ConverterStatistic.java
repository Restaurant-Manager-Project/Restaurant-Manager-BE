package com.example.Restaurant_Manager_BE.converters;

import com.example.Restaurant_Manager_BE.dto.StatisticDTO.ProductStatisticDTO;
import com.example.Restaurant_Manager_BE.dto.StatisticDTO.RevenueStatisticDTO;
import com.example.Restaurant_Manager_BE.entities.ProductEntity;

import java.util.List;

public interface ConverterStatistic {
    ProductStatisticDTO toProductStatisticDTO(Object[] query_result);
    List<ProductStatisticDTO> ProductStatisticDTO_List(List<Object[]> results);
    RevenueStatisticDTO toRevenueStatisticDTO(Object[] query_result);
    List<RevenueStatisticDTO> RevenueStatisticDTO_List(List<Object[]> results);
}
