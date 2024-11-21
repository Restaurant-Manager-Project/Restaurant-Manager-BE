package com.example.Restaurant_Manager_BE.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImportDTO {
    private Long id;
    private Long employeeId;
    private String employeeName;
    private Long supplierId;
    private String supplierName;
    private Date dateCreate;
    private Long total;

    @JsonProperty("detailsProductList")
    private List<DetailsImportDTO> detailsImportDTOList;

}
