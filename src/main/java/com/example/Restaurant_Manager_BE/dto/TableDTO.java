package com.example.Restaurant_Manager_BE.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableDTO {
    private Long id;
    private String name;
    private String statusId;
    private String statusName;
    private String direction;
}
