package com.example.Restaurant_Manager_BE.dto.response;

import java.io.ObjectInputFilter.Status;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class TableResponse {
    private Long id;
    private String name;
    private String statusName;
    private String direction;
}
