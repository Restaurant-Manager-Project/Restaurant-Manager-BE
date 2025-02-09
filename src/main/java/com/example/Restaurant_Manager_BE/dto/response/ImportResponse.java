package com.example.Restaurant_Manager_BE.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ImportResponse {
    private Long id;
    private String supplierName;
    private String employeeName;
    private String dateCreate;
    private Long total;
    private List<DetailsImportResponse> detailsImport;
}
