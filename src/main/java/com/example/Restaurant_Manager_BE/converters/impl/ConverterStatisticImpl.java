package com.example.Restaurant_Manager_BE.converters.impl;

import com.example.Restaurant_Manager_BE.converters.ConverterStatistic;
import com.example.Restaurant_Manager_BE.dto.StatisticDTO.ProductStatisticDTO;
import com.example.Restaurant_Manager_BE.dto.StatisticDTO.RevenueStatisticDTO;
import com.example.Restaurant_Manager_BE.entities.ProductEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class ConverterStatisticImpl implements ConverterStatistic {
    @Override
    public ProductStatisticDTO toProductStatisticDTO(Object[] result) {
        if (result == null || result.length < 4) return null;
        return ProductStatisticDTO.builder()
                .id((Long) result[0])
                .name((String) result[1])
                .quantity_sold(((Number) result[2]).intValue())
                .rank(((Number) result[3]).intValue())
                .build();
    }
    @Override
    public List<ProductStatisticDTO> ProductStatisticDTO_List(List<Object[]> results) {
        if (results == null || results.isEmpty()) return List.of(); // Trả về danh sách rỗng nếu null hoặc trống
        return results.stream()
                .map(this::toProductStatisticDTO) // Gọi lại hàm `toProductStatisticDTO`
                .filter(Objects::nonNull) // Loại bỏ null nếu có
                .collect(Collectors.toList());
    }

    @Override
    public RevenueStatisticDTO toRevenueStatisticDTO(Object[] queryResult) {
       if (queryResult == null) return null;
       return RevenueStatisticDTO.builder()
               .months((Integer) queryResult[0])  // Tháng đã được ép kiểu đúng ở custom repo
               .revenue((Long) queryResult[1])
               .build();
    }

    @Override
    public List<RevenueStatisticDTO> RevenueStatisticDTO_List(List<Object[]> results) {
        if(results == null || results.isEmpty()) return List.of();
        return results.stream()
                .map(this::toRevenueStatisticDTO)
                .collect(Collectors.toList());
    }
}
