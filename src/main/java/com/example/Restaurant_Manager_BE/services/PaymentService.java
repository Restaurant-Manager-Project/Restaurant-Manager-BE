package com.example.Restaurant_Manager_BE.services;

import com.example.Restaurant_Manager_BE.configurations.VNPAYConfig;
import com.example.Restaurant_Manager_BE.responses.PaymentResponse;
import com.example.Restaurant_Manager_BE.utils.VNPAYUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class PaymentService {
    private final VNPAYConfig vnPayConfig;
    public ResponseEntity<PaymentResponse> createVnPayPayment(Map<String, Object> reqData, HttpServletRequest request) {
        long amount = (Integer) (reqData.get("amount")) * 100L;
        String directionTable =  reqData.get("directionTable").toString();
        String clientId = reqData.get("clientId") == null ? "" : reqData.get("clientId").toString();
        String bankCode = reqData.getOrDefault("bankCode", "").toString();
        Map<String, String> vnpParamsMap = vnPayConfig.getVNPayConfig();

        vnpParamsMap.put("vnp_Amount", String.valueOf(amount));
        if (bankCode != null && !bankCode.isEmpty()) {
            vnpParamsMap.put("vnp_BankCode", bankCode);
        }
        vnpParamsMap.put("vnp_IpAddr", VNPAYUtil.getIpAddress(request));
        //
        vnpParamsMap.put("vnp_OrderInfo", directionTable + "_" + clientId);
        vnpParamsMap.put("vnp_TxnRef", directionTable);
        //
        String queryUrl = VNPAYUtil.getPaymentURL(vnpParamsMap, true);
        String hashData = VNPAYUtil.getPaymentURL(vnpParamsMap, false);
        String vnpSecureHash = VNPAYUtil.hmacSHA512(vnPayConfig.getSecretKey(), hashData);
        queryUrl += "&vnp_SecureHash=" + vnpSecureHash;
        String paymentUrl = vnPayConfig.getVnp_PayUrl() + "?" + queryUrl;
         PaymentResponse paymentResponse = PaymentResponse.builder()
                                                            .message("success")
                                                            .amount(amount)
                                                            .paymentUrl(paymentUrl)
                                                            .build();
        return ResponseEntity.ok(paymentResponse);

    }
}
