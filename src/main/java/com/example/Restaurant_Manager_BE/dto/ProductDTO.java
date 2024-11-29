package com.example.Restaurant_Manager_BE.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class ProductDTO {

    private Long id;
    private String name;
    private String description;
    private String img;
    private Long price;
    private String categoryName;
    private Long categoryId;
    private Long quantity;
    private Long quantity_from_import;
}
