package com.example.Restaurant_Manager_BE.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class OrderRequest {
    private String directionTable;
    private Date dateCreate;
    private Long total;
    private Long processId;
    @JsonProperty("detailList")
    private List<DetailOrderRequest> detailsOrderList;
}
