package com.example.Restaurant_Manager_BE.services.impl;

import com.example.Restaurant_Manager_BE.constants.MessageKeys;
import com.example.Restaurant_Manager_BE.converters.ConverterOrder;
import com.example.Restaurant_Manager_BE.dto.DetailsOrderDTO;
import com.example.Restaurant_Manager_BE.entities.*;
import com.example.Restaurant_Manager_BE.dto.OrderDTO;
import com.example.Restaurant_Manager_BE.enums.StatusOrder;
import com.example.Restaurant_Manager_BE.exceptions.DataNotFoundException;
import com.example.Restaurant_Manager_BE.repositories.*;
import com.example.Restaurant_Manager_BE.repositories.Custom.ProcessRepository;
import com.example.Restaurant_Manager_BE.responses.APIResponse;
import com.example.Restaurant_Manager_BE.services.OrderService;
import com.example.Restaurant_Manager_BE.utils.LocalizationUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final TableRepository tableRepository;
    private final LocalizationUtils localizationUtils;
    private final ConverterOrder converterOrder;
    private final ProcessRepository processRepository;

    @Override
    public ResponseEntity<APIResponse> createOrder(OrderDTO orderDTO) {
        OrderEntity orderEntity = converterOrder.toEntity(orderDTO);
        orderEntity.setProcess(processRepository.getById(StatusOrder.RECEIVED.getId()));
        orderRepository.save(orderEntity);
        APIResponse APIResponse = new APIResponse();
        APIResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.ORDER_CREATE_SUCCESS));
        return ResponseEntity.status(HttpStatus.OK).body(APIResponse);
    }


    @Override
    public ResponseEntity<APIResponse> getOrdersByDirection(String direction) {
    List<OrderEntity> orderList = orderRepository.getAllOrderWithDetailsByDirectionTable(direction);
    if (orderList.isEmpty()) {
        throw new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKeys.ORDER_NOT_FOUND));
    }
    List<OrderDTO> orderDTOList = converterOrder.toDTOList(orderList);
    APIResponse APIResponse = new APIResponse();
    APIResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.ORDER_LIST_GET_SUCCESS));
    APIResponse.setResult(orderDTOList);
    return ResponseEntity.status(HttpStatus.OK).body(APIResponse);

}

    @Override
    public ResponseEntity<APIResponse> getAllOrders() {
        List<OrderEntity> listOrder = orderRepository.getAllOrderWithTableAndProcess();
        listOrder.forEach(orderEntity -> {
            orderEntity.setDetailsOrderList(null);
        });
        if (listOrder.isEmpty()) {
            throw new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKeys.ORDER_NOT_FOUND));
        }
        List<OrderDTO> orderDTOList = converterOrder.toDTOList(listOrder);
        APIResponse APIResponse = new APIResponse();
        APIResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.ORDER_LIST_GET_SUCCESS));
        APIResponse.setResult(orderDTOList);
        return ResponseEntity.ok(APIResponse);
    }

    @Override
    public ResponseEntity<APIResponse> updateProcessOfOrder(Long order_id , Long process_id){
        OrderEntity orderEntity = orderRepository.findById(order_id)
                .orElseThrow(() -> new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKeys.ORDER_NOT_FOUND)));
        ProcessEntity processEntity = processRepository.findById(process_id)
                .orElseThrow(()-> new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKeys.PROCESS_NOT_FOUND)));
        orderEntity.setProcess(processEntity);
        APIResponse APIResponse = new APIResponse();
        APIResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.ORDER_LIST_GET_SUCCESS));
        orderRepository.save(orderEntity);
        return ResponseEntity.ok(APIResponse);
    }
}
