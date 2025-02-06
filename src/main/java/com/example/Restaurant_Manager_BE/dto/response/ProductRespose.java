package com.example.Restaurant_Manager_BE.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductRespose {
    private Long id;
    private String name;
    private String description;
    private String categoryName;
    private String img;
    private Long quantity;
    private Long price;
}
