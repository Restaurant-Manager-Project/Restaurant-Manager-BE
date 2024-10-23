package com.example.Restaurant_Manager_BE.controllers;


import com.example.Restaurant_Manager_BE.constants.MessageKeys;
import com.example.Restaurant_Manager_BE.exceptions.ErrorCode;
import com.example.Restaurant_Manager_BE.responses.APIResponse;
import com.example.Restaurant_Manager_BE.exceptions.DataNotFoundException;
import com.example.Restaurant_Manager_BE.dto.DetailsOrderDTO;
import com.example.Restaurant_Manager_BE.dto.OrderDTO;
import com.example.Restaurant_Manager_BE.services.DetailsOrderService;
import com.example.Restaurant_Manager_BE.services.OrderService;
import com.example.Restaurant_Manager_BE.utils.LocalizationUtils;
import io.swagger.v3.oas.annotations.Operation;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final DetailsOrderService detailsOrderService;
    private final LocalizationUtils localizationUtils;
    @Operation(summary = "Thêm order", description = "Tạo order gồm các chi tiết món ăn khi gọi món")
    @PostMapping("/api/order")
    public ResponseEntity<APIResponse> orderProduct(@RequestBody OrderDTO orderDTO) {
        if (orderDTO == null) {
            throw new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKeys.ORDER_CREATE_FAILED));
        }

        return orderService.createOrder(orderDTO);
    }

    @GetMapping("/api/orders")
    public ResponseEntity<APIResponse> test(@RequestParam(name = "direction") String direction) {
        return orderService.getOrdersByDirection(direction);
    }


}

