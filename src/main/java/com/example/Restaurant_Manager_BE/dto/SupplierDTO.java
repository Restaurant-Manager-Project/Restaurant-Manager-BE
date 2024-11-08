package com.example.Restaurant_Manager_BE.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SupplierDTO {
    private Long id;
    private String name;
    private String address;
    private String phone;
    @JsonProperty("importList")
    private List<ImportDTO> importDTOList;
}
