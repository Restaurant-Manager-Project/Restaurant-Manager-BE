package com.example.Restaurant_Manager_BE.controllers;

import com.example.Restaurant_Manager_BE.dto.InvoiceDTO;
import com.example.Restaurant_Manager_BE.dto.OrderDTO;
import com.example.Restaurant_Manager_BE.responses.APIResponse;
import com.example.Restaurant_Manager_BE.responses.PaymentResponse;
import com.example.Restaurant_Manager_BE.services.InvoiceService;
import com.example.Restaurant_Manager_BE.services.PaymentService;
import com.example.Restaurant_Manager_BE.services.OrderService;
import com.example.Restaurant_Manager_BE.services.TableService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;
    private final OrderService orderService;
    private final TableService tableService;
    private final InvoiceService invoiceService;
    private final ModelMapper modelMapper;
    @PostMapping("/vnpay")
    public ResponseEntity<PaymentResponse> pay(@RequestBody Map<String, Object> reqData, HttpServletRequest request) {
        return  paymentService.createVnPayPayment(reqData, request);
    }
    @GetMapping("/vnpay-callback")
    public ResponseEntity<PaymentResponse> payCallbackHandler(HttpServletRequest request) {
        String status = request.getParameter("vnp_ResponseCode");
        String[] listOrder = request.getParameter("vnp_OrderInfo").split("_");
        String direction = listOrder[0];
        Long clientId = Long.parseLong(listOrder[1]);
        long amount = Long.parseLong(request.getParameter("vnp_Amount")) / 100;
        if (status.equals("00")) {
            List<OrderDTO> test = modelMapper.map(orderService.getOrdersByDirection(direction).getBody().getResult(), List.class);;
            InvoiceDTO invoiceDTO = InvoiceDTO.builder()
                    .total(amount)
                    .employeeId(1L)
                    .clientId(clientId)
                    .timeCreate(new Date())
                    .OrderDTOList(test)
                    .build();
            tableService.generateDirection(direction);
            return ResponseEntity.ok(PaymentResponse.builder()
                    .code(status)
                    .message("Success")
                    .amount(amount)
                    .direction(direction)
                    .resData(invoiceService.createInvoice(invoiceDTO).getBody())
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
