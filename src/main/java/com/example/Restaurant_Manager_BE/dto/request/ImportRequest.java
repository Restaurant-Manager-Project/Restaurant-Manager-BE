package com.example.Restaurant_Manager_BE.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ImportRequest {

    private Long supplierId;
    private Long employeeId;
    private String date;
    private Long total;
    private List<DetailsImportRequest> detailsImportRequests;
}
