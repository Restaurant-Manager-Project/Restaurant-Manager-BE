package com.example.Restaurant_Manager_BE.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;


@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderResponse {
    private Long id;
    private Long tableId;
    private String nameTable;
    private String dateCreate;
    private Long total;
    private String directionTable;
    private String processName;
    @JsonProperty("detailList")
    private List<DetailOrderResponse> detailsOrderList;
}
