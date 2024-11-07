package com.example.Restaurant_Manager_BE.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
    private Boolean gender;
    private String accountUsername;
    @JsonProperty("importList")
    private List<ImportDTO> importDTOList;

    @JsonProperty("invoiceList")
    private List<InvoiceDTO> invoiceDTOList;
}
