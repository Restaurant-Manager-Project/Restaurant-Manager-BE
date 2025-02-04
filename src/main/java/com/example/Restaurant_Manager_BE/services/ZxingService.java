package com.example.Restaurant_Manager_BE.services;


import org.springframework.web.multipart.MultipartFile;

public interface ZxingService {

    String generateQrCode(String direction);
    String generatePassword(int length);

}
