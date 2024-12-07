package com.example.Restaurant_Manager_BE.services.impl;

import com.example.Restaurant_Manager_BE.constants.MessageKeys;
import com.example.Restaurant_Manager_BE.converters.ConverterOrder;
import com.example.Restaurant_Manager_BE.dto.DetailsOrderDTO;
import com.example.Restaurant_Manager_BE.entities.*;
import com.example.Restaurant_Manager_BE.dto.OrderDTO;
import com.example.Restaurant_Manager_BE.enums.StatusOrder;
import com.example.Restaurant_Manager_BE.exceptions.DataNotFoundException;
import com.example.Restaurant_Manager_BE.exceptions.OutOfStockException;
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
import org.springframework.transaction.annotation.Transactional;

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


    private void updateStockProduct(List<DetailsOrderEntity> listDetail) {
        listDetail.forEach(detail -> {
            ProductEntity productEntity = productRepository.findById(detail.getProduct().getId())
                    .orElseThrow(() -> new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKeys.PRODUCT_NOT_EXISTED)));
            if (detail.getQuantity() > productEntity.getQuantity()) {
                throw new OutOfStockException(localizationUtils.getLocalizedMessage(MessageKeys.PRODUCT_OUT_OF_STOCK));
            }
            else {
                productEntity.setQuantity(productEntity.getQuantity() - detail.getQuantity());
                productRepository.save(productEntity);
            }
        });
    }

    @Override
    @Transactional
    public ResponseEntity<APIResponse> createOrder(OrderDTO orderDTO) {

        OrderEntity orderEntity = converterOrder.toEntity(orderDTO);
        orderEntity.setProcess(processRepository.getById(StatusOrder.RECEIVED.getId()));
        updateStockProduct(orderEntity.getDetailsOrderList());
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
    public ResponseEntity<APIResponse> getOrderById(Long id) {
        OrderEntity orderEntity = orderRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKeys.ORDER_NOT_FOUND)));
        OrderDTO orderDTO = converterOrder.toDTO(orderEntity);
        APIResponse APIResponse = new APIResponse();
        APIResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.ORDER_GET_SUCCESS));
        APIResponse.setResult(orderDTO);
        return ResponseEntity.ok(APIResponse);
    }

    @Override
    public ResponseEntity<APIResponse> updateOrder(Long id, OrderDTO orderDTO) {
        OrderEntity orderEntity = orderRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKeys.ORDER_NOT_FOUND)));
        orderEntity.setProcess(processRepository.getById(orderDTO.getProcessId()));
        orderRepository.save(orderEntity);
        APIResponse APIResponse = new APIResponse();
        APIResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.ORDER_UPDATE_SUCCESS));
        return ResponseEntity.ok(APIResponse);
    }
}
