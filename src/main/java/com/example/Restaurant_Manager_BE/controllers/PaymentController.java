package com.example.Restaurant_Manager_BE.controllers;

import com.example.Restaurant_Manager_BE.dto.request.InvoiceRequest;
import com.example.Restaurant_Manager_BE.dto.request.OrderRequest;
import com.example.Restaurant_Manager_BE.mapper.response.OrderResponseMapper;
import com.example.Restaurant_Manager_BE.responses.PaymentResponse;
import com.example.Restaurant_Manager_BE.services.InvoiceService;
import com.example.Restaurant_Manager_BE.services.PaymentService;
import com.example.Restaurant_Manager_BE.services.OrderService;
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
    private final InvoiceService invoiceService;
    private final OrderResponseMapper orderResponseMapper;
    @PostMapping("/vnpay")
    public ResponseEntity<PaymentResponse> pay(@RequestBody Map<String, Object> reqData, HttpServletRequest request) {
        return  paymentService.createVnPayPayment(reqData, request);
    }
    @GetMapping("/vnpay-callback")
    public ResponseEntity<PaymentResponse> payCallbackHandler(HttpServletRequest request) {
        String status = request.getParameter("vnp_ResponseCode");
        String[] listOrder = request.getParameter("vnp_OrderInfo").split("_");
        String direction = listOrder[0];
        Long clientId = listOrder.length == 2 ? Long.parseLong(listOrder[1]) : null;
        long amount = Long.parseLong(request.getParameter("vnp_Amount")) / 100;
        if (status.equals("00")) {
            InvoiceRequest invoiceRequest = InvoiceRequest.builder()
                    .total(amount)
                    .employeeId(1L)
                    .clientId(clientId)
                    .timeCreate(new Date())
                    .direction(direction)
                    .build();
            return ResponseEntity.ok(PaymentResponse.builder()
                    .code(status)
                    .message("Success")
                    .amount(amount)
                    .direction(direction)
                    .resData(invoiceService.createInvoice(invoiceRequest))
                    .build());
        }
        return ResponseEntity.ok(PaymentResponse.builder()
                .code(status)
                .message("Failed")
                .amount(amount)
                .direction(direction)
                .build());

    }


}
