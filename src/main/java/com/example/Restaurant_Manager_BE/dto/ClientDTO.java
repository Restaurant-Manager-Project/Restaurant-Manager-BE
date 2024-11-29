package com.example.Restaurant_Manager_BE.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class ClientDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private Long rank_id;
    private Long paid;
}
