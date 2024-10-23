package com.example.Restaurant_Manager_BE.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class APIResponse {

    private boolean success = true;
    private Integer code;
    private String message;
    private Object result;


}
