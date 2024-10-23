package com.example.Restaurant_Manager_BE.controllers;

import com.example.Restaurant_Manager_BE.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DetailsOrderController {
    @Autowired
    private OrderService orderService;

//    @GetMapping("/detailsOrder")
//    public ResponseEntity<MessageResponse> detailsOrder(@RequestParam(name = "direction") String direction) {
//        MessageResponse messageResponse = new MessageResponse();
//
//
//        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
//    }


}
