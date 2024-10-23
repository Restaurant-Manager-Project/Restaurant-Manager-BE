package com.example.Restaurant_Manager_BE.service;

import com.example.Restaurant_Manager_BE.model.DetailsOrderModel;

import java.util.List;

public interface DetailsOrderService {
    public List<DetailsOrderModel> mergeDetailsOrder(List<Long> listOrderID);
}
