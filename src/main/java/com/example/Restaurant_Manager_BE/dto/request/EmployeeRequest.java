package com.example.Restaurant_Manager_BE.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class EmployeeRequest {
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    private Boolean gender;
}
