package com.example.Restaurant_Manager_BE.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetailsImportResponse {
        private Long id;
        private String productName;
        private Integer quantity;
        private Long importPrice;
}
