package com.example.Restaurant_Manager_BE.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;


@Getter
@Setter
public class CategoryRequest {
    @NotBlank(message = "Không được để trống")
    @NotNull(message = "Không được để trống 233")
    private String name;
    private MultipartFile img;
}
