package com.example.Restaurant_Manager_BE.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {
    private Long orderId;
    private Long tableId;
    private String nameTable;
    private Date dateCreate;
    private Long total;
    private String directionTable;
    private Long processId;
    private String processName;
    @JsonProperty("detailList")
    private List<DetailsOrderDTO> detailsOrderDTOList;

}
