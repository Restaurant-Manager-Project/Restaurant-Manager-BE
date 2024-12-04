package com.example.Restaurant_Manager_BE.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class EmployeeDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    private Boolean gender;
    private AccountDTO accountDTO;

}
