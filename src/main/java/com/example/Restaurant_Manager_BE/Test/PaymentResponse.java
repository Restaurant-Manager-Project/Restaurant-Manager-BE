package com.example.Restaurant_Manager_BE.Test;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentResponse {
    public String code;
    public String message;
    public String paymentUrl;
    public long amount;
}
