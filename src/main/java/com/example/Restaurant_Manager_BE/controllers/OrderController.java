package com.example.Restaurant_Manager_BE.controllers;


import com.example.Restaurant_Manager_BE.constants.MessageKeys;
import com.example.Restaurant_Manager_BE.responses.APIResponse;
import com.example.Restaurant_Manager_BE.exceptions.DataNotFoundException;
import com.example.Restaurant_Manager_BE.dto.OrderDTO;
import com.example.Restaurant_Manager_BE.services.DetailsOrderService;
import com.example.Restaurant_Manager_BE.services.OrderService;
import com.example.Restaurant_Manager_BE.utils.LocalizationUtils;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


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
        public ResponseEntity<APIResponse> getOrderByDirection(@RequestParam(name = "direction") String direction) {
            return orderService.getOrdersByDirection(direction);
        }

        @PreAuthorize("hasRole('order.view')")
        @GetMapping("/api/orders/{id}")
        public ResponseEntity<APIResponse> getOrderById(@PathVariable Long id) {
            return orderService.getOrderById(id);
        }

        @PreAuthorize("hasRole('order.view')")
        @GetMapping("/api/orders/")
        public ResponseEntity<APIResponse> getAllOrders() {
            return orderService.getAllOrders();
        }

        @PreAuthorize("hasRole('order.update')")
        @PutMapping("/api/orders/{id}")
        public ResponseEntity<APIResponse> updateOrder(@PathVariable Long id, @RequestBody OrderDTO orderDTO) {
            return orderService.updateOrder(id, orderDTO);
        }

}

