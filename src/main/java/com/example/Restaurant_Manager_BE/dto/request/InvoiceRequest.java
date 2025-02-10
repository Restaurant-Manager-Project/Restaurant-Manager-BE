package com.example.Restaurant_Manager_BE.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Setter
@Getter
public class InvoiceRequest {
    private Long employeeId;
    private Long clientId;
    private Date timeCreate;
    private Long total;
}
