package com.example.Restaurant_Manager_BE.dto.response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetailOrderResponse {
    private Long id;
    private Long productId;
    private Long orderId;
    private String productName;
    private Integer quantity;
    private Long price;
}
