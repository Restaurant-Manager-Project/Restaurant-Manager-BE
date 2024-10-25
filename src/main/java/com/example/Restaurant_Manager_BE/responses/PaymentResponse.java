package com.example.Restaurant_Manager_BE.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentResponse {
    private  String code;
    private String message;
    private long amount;
    private String paymentUrl;
    private String direction;
    private Object resData;

}
