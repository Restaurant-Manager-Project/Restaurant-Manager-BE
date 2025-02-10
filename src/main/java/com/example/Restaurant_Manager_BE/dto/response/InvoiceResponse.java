package com.example.Restaurant_Manager_BE.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Setter
@Getter
public class InvoiceResponse {
    private String employeeName;
    private String clientName;
    private String timeCreate;
    private Long total;
}
