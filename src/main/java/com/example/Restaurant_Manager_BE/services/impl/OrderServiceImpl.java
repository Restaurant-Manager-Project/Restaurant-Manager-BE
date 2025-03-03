package com.example.Restaurant_Manager_BE.services.impl;

import com.example.Restaurant_Manager_BE.constants.MessageKeys;
import com.example.Restaurant_Manager_BE.dto.request.OrderRequest;
import com.example.Restaurant_Manager_BE.dto.response.OrderResponse;
import com.example.Restaurant_Manager_BE.entities.*;
import com.example.Restaurant_Manager_BE.enums.StatusOrder;
import com.example.Restaurant_Manager_BE.enums.StatusTable;
import com.example.Restaurant_Manager_BE.exceptions.DataNotFoundException;
import com.example.Restaurant_Manager_BE.exceptions.OutOfStockException;
import com.example.Restaurant_Manager_BE.mapper.request.OrderRequestMapper;
import com.example.Restaurant_Manager_BE.mapper.response.OrderResponseMapper;

import com.example.Restaurant_Manager_BE.repositories.*;
import com.example.Restaurant_Manager_BE.services.OrderService;
import com.example.Restaurant_Manager_BE.utils.LocalizationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final TableRepository tableRepository;
    private final LocalizationUtils localizationUtils;
    private final OrderResponseMapper orderResponseMapper;
    private final OrderRequestMapper orderRequestMapper;


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
    public boolean createOrder(OrderRequest orderRequest) {
        OrderEntity orderEntity = orderRequestMapper.toEntity(orderRequest);
        TableEntity tableEntity = tableRepository.findByDirection(orderEntity.getDirectionTable())
                .orElseThrow(() -> new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKeys.TABLE_NOT_FOUND)));
        tableEntity.setStatusTable(StatusTable.AVAILABLE);
        orderEntity.setTable(tableEntity);
        orderEntity.setProcess(StatusOrder.RECEIVED);
        tableRepository.save(tableEntity);
        List<DetailsOrderEntity> listDetails = orderEntity.getDetailsOrderList();
        for (DetailsOrderEntity detail : listDetails) {
            detail.setOrder(orderEntity);
        }
        updateStockProduct(listDetails);
        return orderRepository.save(orderEntity) != null ? true : false;
    }


    @Override
    public List<OrderResponse> getOrdersByDirection(String direction) {
        List<OrderEntity> orderEntity = orderRepository.getAllOrderWithDetailsByDirectionTable(direction);
        return orderResponseMapper.toListDto(orderEntity);
    }

    @Override
    public List<OrderResponse> getAllOrders() {
        List<OrderEntity> listOrder = orderRepository.getAllOrderWithTableAndProcess();
        listOrder.forEach(orderEntity -> {
            orderEntity.setDetailsOrderList(null);
        });
        if (listOrder.isEmpty()) {
            throw new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKeys.ORDER_NOT_FOUND));
        }
        List<OrderResponse> test = orderResponseMapper.toListDto(listOrder);
        return orderResponseMapper.toListDto(listOrder);
    }

    @Override
    public OrderResponse getOrderById(Long id) {
        OrderEntity orderEntity = orderRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKeys.ORDER_NOT_FOUND)));
        return orderResponseMapper.toDto(orderEntity);
    }

    @Override
    public boolean updateOrder(Long id, OrderRequest orderRequest) {
        OrderEntity orderEntity = orderRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKeys.ORDER_NOT_FOUND)));
//        orderEntity.setProcess(processRepository.getById(orderRequest.getProcessId()));
        return orderRepository.save(orderEntity) != null ? true : false;
    }
}
