package com.example.Restaurant_Manager_BE.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetailsImportDTO {
    private Long id;
    private Long productId;
    private Long importId;
    private Long quantity;
    private Long importPrice;
    private Long sellPrice;
}
