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
public class InvoiceDTO {
    private Long Id;
    private Long employeeId;
    private Long clientId;
    private Date timeCreate;
    private Long total;
    @JsonProperty("orderList")
    private List<OrderDTO> OrderDTOList;

}
