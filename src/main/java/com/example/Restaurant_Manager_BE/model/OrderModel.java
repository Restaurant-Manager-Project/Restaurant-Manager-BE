package com.example.Restaurant_Manager_BE.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

public class OrderModel {

    private String client_id;
    private Date date_create;
    private Long total;
    @JsonProperty("details_list")
    private List<DetailsProductModel> detailsProductModelList;



    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public Date getDate_create() {
        return date_create;
    }

    public void setDate_create(Date date_create) {
        this.date_create = date_create;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<DetailsProductModel> getDetailsProductModelList() {
        return detailsProductModelList;
    }

    public void setDetailsProductModelList(List<DetailsProductModel> detailsProductModelList) {
        this.detailsProductModelList = detailsProductModelList;
    }
}
