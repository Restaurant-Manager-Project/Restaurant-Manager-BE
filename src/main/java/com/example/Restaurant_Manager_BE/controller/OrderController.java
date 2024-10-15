package com.example.Restaurant_Manager_BE.controller;


import com.example.Restaurant_Manager_BE.exception.MessageRespone;
import com.example.Restaurant_Manager_BE.model.DetailsProductModel;
import com.example.Restaurant_Manager_BE.model.OrderModel;
import com.example.Restaurant_Manager_BE.service.OrdersService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class OrderController {
    @Autowired
    private OrdersService ordersService;
    @Operation(summary = "Thêm order", description = "Tạo order gồm các chi tiết món ăn khi gọi món")
    @PostMapping("/api/order")
    public MessageRespone orderProduct(@RequestBody OrderModel orderModel) {

        MessageRespone messageRespone = ordersService.createOrder(orderModel);
        return messageRespone;
    }
    @Operation(summary = "Xem danh sách chi tiết của order", description = "Xem danh sách chi tiết của {order_id}")
    @GetMapping("/api/{order_id}/details")
    public void detailsProduct(@RequestBody Map<String, String> params) {

    }
}

