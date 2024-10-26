package com.example.Restaurant_Manager_BE.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriesDTO {
    private int id;
    private String name;
}
