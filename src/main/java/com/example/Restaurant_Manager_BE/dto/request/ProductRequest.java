package com.example.Restaurant_Manager_BE.dto.request;


import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ProductRequest {
    private String name;
    private String description;
    private String categoryName;
    private Long price;
    private MultipartFile img;
}
