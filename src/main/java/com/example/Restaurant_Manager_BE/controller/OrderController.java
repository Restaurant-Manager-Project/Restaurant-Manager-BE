package com.example.Restaurant_Manager_BE.controller;


import com.example.Restaurant_Manager_BE.exception.MessageRespone;
import com.example.Restaurant_Manager_BE.model.DetailsProductModel;
import com.example.Restaurant_Manager_BE.model.OrderModel;
import com.example.Restaurant_Manager_BE.service.OrdersService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    @Operation(summary = "Thêm danh sách chi tiết", description = "Thêm danh sách chi tiết cho {order_id}. Khi order thêm món")
    @PostMapping("/api/{order_id}/order")
    public void detailsProduct(@RequestBody Map<String, String> params) {

    }
}

