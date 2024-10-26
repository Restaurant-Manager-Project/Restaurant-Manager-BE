package com.example.Restaurant_Manager_BE.controllers;

import com.example.Restaurant_Manager_BE.responses.PaymentResponse;
import com.example.Restaurant_Manager_BE.services.PaymentService;
import com.example.Restaurant_Manager_BE.services.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;
    private final OrderService orderService;
    @PostMapping("/vn-pay")
    public ResponseEntity<PaymentResponse> pay(@RequestBody Map<String, Object> reqData, HttpServletRequest request) {
        return  paymentService.createVnPayPayment(reqData, request);
    }
    @GetMapping("/vn-pay-callback")
    public ResponseEntity<PaymentResponse> payCallbackHandler(HttpServletRequest request) {
        String status = request.getParameter("vnp_ResponseCode");
        String direction = request.getParameter("vnp_OrderInfo");
        long amount = Long.parseLong(request.getParameter("vnp_Amount")) / 100;
        System.out.println("status: " + status);
        System.out.println("direction: " + direction);
        if (status.equals("00")) {
            System.out.println("0");
            return ResponseEntity.ok(PaymentResponse.builder()
                    .code(status)
                    .message("Success")
                    .amount(amount)
                    .direction(direction)
                    .resData(orderService.getOrdersByDirection(direction).getBody())
                    .build());
        }
        else {
            System.out.println("1");
            return ResponseEntity.ok(PaymentResponse.builder()
                    .code(status)
                    .message("Failed")
                    .amount(amount)
                    .direction(direction)
                    .build());
        }
    }
    

}
