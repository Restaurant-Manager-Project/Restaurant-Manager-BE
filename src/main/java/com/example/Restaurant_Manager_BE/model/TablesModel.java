package com.example.Restaurant_Manager_BE.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TablesModel {
    private Long id;
    private String name;
    private String statusId;
    private String statusName;
}
