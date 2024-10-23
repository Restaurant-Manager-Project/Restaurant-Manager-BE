package com.example.Restaurant_Manager_BE.service;
import com.example.Restaurant_Manager_BE.exception.MessageResponse;
import com.example.Restaurant_Manager_BE.model.TablesModel;


public interface TablesService {
    TablesModel findByDirection(String direction);

    String generateQRCode(Long id);

}
