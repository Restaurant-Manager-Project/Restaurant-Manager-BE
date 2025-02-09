package com.example.Restaurant_Manager_BE.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupplierRequest {
    private String name;
    private String address;
    private String phone;
}
