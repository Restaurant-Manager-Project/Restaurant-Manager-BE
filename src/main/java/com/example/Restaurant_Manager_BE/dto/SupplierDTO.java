package com.example.Restaurant_Manager_BE.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierDTO {
    private String name;
    private String address;
    private String phone;
    @JsonProperty("importList")
    private List<ImportDTO> importDTOList;
}
