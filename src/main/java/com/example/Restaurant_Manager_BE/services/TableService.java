package com.example.Restaurant_Manager_BE.services;
import com.example.Restaurant_Manager_BE.dto.TableDTO;


public interface TableService {
    TableDTO findByDirection(String direction);

    String generateQRCode(Long id);

}
