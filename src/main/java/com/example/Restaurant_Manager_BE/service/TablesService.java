package com.example.Restaurant_Manager_BE.service;
import com.example.Restaurant_Manager_BE.exception.MessageResponse;

public interface TablesService {

    MessageResponse findByDirection(String direction);
    MessageResponse generateDirection(Long id);

    MessageResponse generateQRCode(Long id);

}
