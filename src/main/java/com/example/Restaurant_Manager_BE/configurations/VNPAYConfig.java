package com.example.Restaurant_Manager_BE.configurations;


import com.example.Restaurant_Manager_BE.utils.VNPAYUtil;
import lombok.Getter;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Configuration

public class VNPAYConfig {

    @Getter
    @Value("${payment.vnPay.url}")
    public String vnp_PayUrl;
    @Value("${payment.vnPay.returnUrl}")
    public String vnp_ReturnUrl;
    @Value("${payment.vnPay.tmnCode}")
    public String vnp_TmnCode;
    @Value("${payment.vnPay.secretKey}")
    @Getter
    public String secretKey;
    @Value("${payment.vnPay.version}")
    public String vnp_Version;
    @Value("${payment.vnPay.command}")
    public String vnp_Command;
    @Value("${payment.vnPay.orderType}")
    public String orderType;
    public Map<String, String> getVNPayConfig() {
        Map<String, String> vnpParamsMap = new HashMap<>();
        vnpParamsMap.put("vnp_Version", this.vnp_Version);
        vnpParamsMap.put("vnp_Command", this.vnp_Command);
        vnpParamsMap.put("vnp_TmnCode", this.vnp_TmnCode);
        vnpParamsMap.put("vnp_CurrCode", "VND");
        vnpParamsMap.put("vnp_TxnRef",  VNPAYUtil.getRandomNumber(8));
        vnpParamsMap.put("vnp_OrderType", this.orderType);
        vnpParamsMap.put("vnp_Locale", "vn");
        vnpParamsMap.put("vnp_ReturnUrl", this.vnp_ReturnUrl);
        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh"));
        String vnpCreateDate = localDateTime.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        vnpParamsMap.put("vnp_CreateDate", vnpCreateDate);
        String vnp_ExpireDate = localDateTime.plusMinutes(15).format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        vnpParamsMap.put("vnp_ExpireDate", vnp_ExpireDate);
        return vnpParamsMap;
    }
}
