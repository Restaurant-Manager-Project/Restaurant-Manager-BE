package com.example.Restaurant_Manager_BE.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientRequest {
    private String firstName;
    private String lastName;
    private String phone;
    private String rankName;

}
