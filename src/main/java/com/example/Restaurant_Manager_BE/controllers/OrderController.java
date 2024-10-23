package com.example.Restaurant_Manager_BE.controllers;


import com.example.Restaurant_Manager_BE.exceptions.ErrorCode;
import com.example.Restaurant_Manager_BE.responses.APIResponse;
import com.example.Restaurant_Manager_BE.exceptions.BussinessException;
import com.example.Restaurant_Manager_BE.dto.DetailsOrderDTO;
import com.example.Restaurant_Manager_BE.dto.OrderDTO;
import com.example.Restaurant_Manager_BE.services.DetailsOrderService;
import com.example.Restaurant_Manager_BE.services.OrderService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Thêm order", description = "Tạo order gồm các chi tiết món ăn khi gọi món")
    @PostMapping("/api/order")
    public ResponseEntity<APIResponse> orderProduct(@RequestBody OrderDTO orderDTO) {
        if (orderDTO == null) {
            throw new BussinessException(ErrorCode.EMPTY_INPUT_VALUE);
        }
        boolean isSucces = orderService.createOrder(orderDTO);
        APIResponse APIResponse = new APIResponse();
        APIResponse.setMessage("Create order success");
        HttpStatus httpStatus = isSucces ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(APIResponse, httpStatus);
    }

    @GetMapping("/api/orders")
    public ResponseEntity<APIResponse> test(@RequestParam(name = "direction") String direction) {
        APIResponse APIResponse = new APIResponse();
        List<OrderDTO> orderDTOS = orderService.getOrdersByDirection(direction);
        List<Long> listOrderID = new ArrayList<>();
        for (OrderDTO orderDTO : orderDTOS) {
            listOrderID.add(orderDTO.getOrderId());
        }
        List<DetailsOrderDTO> detailsOrderDTOS = detailsOrderService.mergeDetailsOrder(listOrderID);
        APIResponse.setResult(detailsOrderDTOS);
        return new ResponseEntity<>(APIResponse, HttpStatus.OK);
    }


}

