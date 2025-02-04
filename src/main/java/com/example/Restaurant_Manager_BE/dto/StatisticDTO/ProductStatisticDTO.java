package com.example.Restaurant_Manager_BE.dto.StatisticDTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class ProductStatisticDTO {
    private Long id;
    private String name;
    private int quantity_sold;
    private int rank;
}
