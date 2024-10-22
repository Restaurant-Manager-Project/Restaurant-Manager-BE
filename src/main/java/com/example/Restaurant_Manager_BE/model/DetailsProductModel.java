package com.example.Restaurant_Manager_BE.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailsProductModel {

    private Long productId;
    private Long orderId;
    private Long price;
    private Integer quantity;



}
