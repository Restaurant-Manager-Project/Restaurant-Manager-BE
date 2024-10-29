package com.example.Restaurant_Manager_BE.controllers;

import com.example.Restaurant_Manager_BE.responses.PaymentResponse;
import com.example.Restaurant_Manager_BE.services.PaymentService;
import com.example.Restaurant_Manager_BE.services.OrderService;
import com.example.Restaurant_Manager_BE.services.TableService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;
    private final OrderService orderService;
    private final TableService tableService;
    @PostMapping("/vnpay")
    public ResponseEntity<PaymentResponse> pay(@RequestBody Map<String, Object> reqData, HttpServletRequest request) {
        return  paymentService.createVnPayPayment(reqData, request);
    }
    @GetMapping("/{direction}/vnpay-callback")
    public ResponseEntity<PaymentResponse> payCallbackHandler(HttpServletRequest request, @PathVariable String direction) {
        String status = request.getParameter("vnp_ResponseCode");
        long amount = Long.parseLong(request.getParameter("vnp_Amount")) / 100;
        System.out.println("status: " + status);
        System.out.println("direction: " + direction);
        if (status.equals("00")) {
            tableService.generateDirection(direction);
            return ResponseEntity.ok(PaymentResponse.builder()
                    .code(status)
                    .message("Success")
                    .amount(amount)
                    .direction(direction)
                    .resData(orderService.getOrdersByDirection(direction).getBody())
                    .build());
        }
        else {
            return ResponseEntity.ok(PaymentResponse.builder()
                    .code(status)
                    .message("Failed")
                    .amount(amount)
                    .direction(direction)
                    .build());
        }
    }
    

}
