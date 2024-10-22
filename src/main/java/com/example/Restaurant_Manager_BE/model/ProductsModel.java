package com.example.Restaurant_Manager_BE.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductsModel {

    private Long id;
    private String name;
    private String description;
    private String img;
    private Long price;
    private String categoryName;


}
