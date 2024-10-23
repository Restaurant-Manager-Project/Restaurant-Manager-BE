package com.example.Restaurant_Manager_BE.services;

import com.example.Restaurant_Manager_BE.dto.DetailsOrderDTO;

import java.util.List;

public interface DetailsOrderService {
    public List<DetailsOrderDTO> mergeDetailsOrder(List<Long> listOrderID);
}
