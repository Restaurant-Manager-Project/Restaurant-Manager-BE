package com.example.Restaurant_Manager_BE.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetailsImportRequest {

    private Long productId;
    private Integer quantity;
    private Long importPrice;
}
