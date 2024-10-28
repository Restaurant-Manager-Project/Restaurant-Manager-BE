package com.example.Restaurant_Manager_BE.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDTO {
    private Long orderId;
    private Long tableId;
    private Date dateCreate;
    private Long total;
    private String directionTable;
    @JsonProperty("detailList")
    private List<DetailsOrderDTO> detailsOrderDTOList;

}
