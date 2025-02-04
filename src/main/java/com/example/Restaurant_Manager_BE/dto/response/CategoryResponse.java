package com.example.Restaurant_Manager_BE.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //create getter, setter, equal, hashcode, tostring
@AllArgsConstructor //create constructor with all argument
@NoArgsConstructor //create constructor with no argument
@JsonInclude(JsonInclude.Include.NON_NULL)//exclude null value in response
@Builder
public class CategoryResponse {
    private String name;
    private String img;
}
