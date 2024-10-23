package com.example.Restaurant_Manager_BE.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailsProductDTO {

    private Long productId;
    private Long price;
    private Integer quantity;



}
