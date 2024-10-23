package com.example.Restaurant_Manager_BE.controller;

import com.example.Restaurant_Manager_BE.exception.MessageResponse;
import com.example.Restaurant_Manager_BE.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DetailsOrderController {
    @Autowired
    private OrdersService ordersService;

//    @GetMapping("/detailsOrder")
//    public ResponseEntity<MessageResponse> detailsOrder(@RequestParam(name = "direction") String direction) {
//        MessageResponse messageResponse = new MessageResponse();
//
//
//        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
//    }


}
