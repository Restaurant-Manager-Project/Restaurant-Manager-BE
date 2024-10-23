package com.example.Restaurant_Manager_BE.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DetailsOrderModel {
    private Long id;
    private Long productId;
    private String productName;
    private Integer quantity;
    private Long price;

}
