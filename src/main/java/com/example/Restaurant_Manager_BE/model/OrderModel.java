package com.example.Restaurant_Manager_BE.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderModel {

    private Long tableId;
    private Long orderId;
    private Date dateCreate;
    private Long total;
    private String directionTable;
    @JsonProperty("detailList")
    private List<DetailsProductModel> detailsProductModelList;



}
