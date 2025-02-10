package com.example.Restaurant_Manager_BE.controllers;


import com.example.Restaurant_Manager_BE.constants.MessageKeys;
import com.example.Restaurant_Manager_BE.dto.request.OrderRequest;
import com.example.Restaurant_Manager_BE.responses.APIResponse;
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
        public ResponseEntity<APIResponse> orderProduct(@RequestBody OrderRequest orderRequest) {
            String message = orderService.createOrder(orderRequest) ?
                    localizationUtils.getLocalizedMessage(MessageKeys.ORDER_CREATE_SUCCESS) :
                    localizationUtils.getLocalizedMessage(MessageKeys.ORDER_CREATE_FAILED);
            return ResponseEntity.ok(APIResponse.builder()
                    .message(message)
                    .build());
        }


        @GetMapping("/api/orders")
        public ResponseEntity<APIResponse> getOrderByDirection(@RequestParam(name = "direction") String direction) {
            return ResponseEntity.ok(APIResponse.builder()
                    .message(localizationUtils.getLocalizedMessage(MessageKeys.ORDER_GET_SUCCESS))
                    .result(orderService.getOrdersByDirection(direction))
                    .build());
        }

        @PreAuthorize("hasAuthority('order.view')")
        @GetMapping("/api/orders/{id}")
        public ResponseEntity<APIResponse> getOrderById(@PathVariable Long id) {
            return ResponseEntity.ok(APIResponse.builder()
                    .message(localizationUtils.getLocalizedMessage(MessageKeys.ORDER_GET_SUCCESS))
                    .result(orderService.getOrderById(id))
                    .build());
        }

        @PreAuthorize("hasAuthority('order.view')")
        @GetMapping("/api/orders/")
        public ResponseEntity<APIResponse> getAllOrders() {
            return ResponseEntity.ok(APIResponse.builder()
                    .message(localizationUtils.getLocalizedMessage(MessageKeys.ORDER_LIST_GET_SUCCESS))
                    .result(orderService.getAllOrders())
                    .build());
        }

        @PreAuthorize("hasAuthority('order.update')")
        @PutMapping("/api/orders/{id}")
        public ResponseEntity<APIResponse> updateOrder(@PathVariable Long id, @RequestBody OrderRequest orderRequest) {
            String message = orderService.updateOrder(id, orderRequest) ?
                    localizationUtils.getLocalizedMessage(MessageKeys.ORDER_UPDATE_SUCCESS) :
                    localizationUtils.getLocalizedMessage(MessageKeys.ORDER_UPDATE_FAILED);
            return ResponseEntity.ok(APIResponse.builder()
                    .message(message)
                    .build());
        }

}

