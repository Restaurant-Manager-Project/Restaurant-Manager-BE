package com.example.Restaurant_Manager_BE.controller;


import com.example.Restaurant_Manager_BE.exception.ErrorCode;
import com.example.Restaurant_Manager_BE.exception.MessageResponse;
import com.example.Restaurant_Manager_BE.exception.BussinessException;
import com.example.Restaurant_Manager_BE.model.DetailsOrderModel;
import com.example.Restaurant_Manager_BE.model.OrderModel;
import com.example.Restaurant_Manager_BE.service.DetailsOrderService;
import com.example.Restaurant_Manager_BE.service.OrdersService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class OrderController {
    @Autowired
    private OrdersService ordersService;

    @Autowired
    private DetailsOrderService detailsOrderService;
    @Operation(summary = "Thêm order", description = "Tạo order gồm các chi tiết món ăn khi gọi món")
    @PostMapping("/api/order")
    public ResponseEntity<MessageResponse> orderProduct(@RequestBody OrderModel orderModel) {
        if (orderModel == null) {
            throw new BussinessException(ErrorCode.EMPTY_INPUT_VALUE);
        }
        boolean isSucces = ordersService.createOrder(orderModel);
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setMessage("Create order success");
        HttpStatus httpStatus = isSucces ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(messageResponse, httpStatus);
    }

    @GetMapping("/api/orders")
    public ResponseEntity<MessageResponse> test(@RequestParam(name = "direction") String direction) {
        MessageResponse messageResponse = new MessageResponse();
        List<OrderModel> orderModels = ordersService.getOrdersByDirection(direction);
        List<Long> listOrderID = new ArrayList<>();
        for (OrderModel orderModel : orderModels) {
            listOrderID.add(orderModel.getOrderId());
        }
        List<DetailsOrderModel> detailsOrderModels = detailsOrderService.mergeDetailsOrder(listOrderID);
        messageResponse.setResult(detailsOrderModels);
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }


}

