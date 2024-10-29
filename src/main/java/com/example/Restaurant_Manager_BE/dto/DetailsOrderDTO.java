package com.example.Restaurant_Manager_BE.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class DetailsOrderDTO {
    private Long id;
    private Long productId;
    private Long orderId;
    private String productName;
    private Integer quantity;
    private Long price;

}
