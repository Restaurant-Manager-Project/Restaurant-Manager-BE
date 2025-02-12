package com.example.Restaurant_Manager_BE.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupplierResponse {
    private Long id;
    private String name;
    private String address;
    private String phone;
}
