package com.example.Restaurant_Manager_BE.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImportDTO {
    private long id;
    private long employeeId;
    private long supplierId;
    private Date dateCreate;
    private long total;

    @JsonProperty("detailsProductList")
    private List<DetailsImportDTO> detailsImportDTOList;
}
