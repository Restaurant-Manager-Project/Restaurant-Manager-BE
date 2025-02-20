package com.example.Restaurant_Manager_BE.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonInclude;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class TableRequest {
    private Long id;
    private String name;
    private Long statusid;
    private String statusname;
    private String direction;
}
