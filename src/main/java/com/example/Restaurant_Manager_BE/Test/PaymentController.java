package com.example.Restaurant_Manager_BE.Test;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @GetMapping("/vn-pay")
    public ResponseEntity<PaymentResponse> pay(HttpServletRequest request) {
        return  paymentService.createVnPayPayment(request);
    }
    @GetMapping("/vn-pay-callback")
    public ResponseEntity<PaymentResponse> payCallbackHandler(HttpServletRequest request) {
        String status = request.getParameter("vnp_ResponseCode");
        long amount = Long.parseLong(request.getParameter("vnp_Amount"));
        if (status.equals("00")) {
            return ResponseEntity.ok(new PaymentResponse("00", "Success" , "", amount));
        } else {
            return ResponseEntity.ok(new PaymentResponse("99", "Fail" , "", amount));
        }
    }

}
