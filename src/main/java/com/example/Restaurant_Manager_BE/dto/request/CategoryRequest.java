package com.example.Restaurant_Manager_BE.dto.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;


@Getter
@Setter
public class CategoryRequest {
    private String name;
    private MultipartFile img;
}
