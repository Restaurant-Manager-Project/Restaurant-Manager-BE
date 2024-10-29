package com.example.Restaurant_Manager_BE.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailsImportDTO {
    private long id;
    private long productId;
    private long importId;
    private long quantity;
    private long importPrice;
}
