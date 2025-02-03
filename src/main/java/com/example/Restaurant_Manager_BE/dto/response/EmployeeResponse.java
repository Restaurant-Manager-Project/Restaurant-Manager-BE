package com.example.Restaurant_Manager_BE.dto.response;

import com.example.Restaurant_Manager_BE.dto.AccountDTO;
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
public class EmployeeResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    private Boolean gender;
    private String role;
}
