package com.example.Restaurant_Manager_BE.services.impl;

import com.example.Restaurant_Manager_BE.constants.MessageKeys;
import com.example.Restaurant_Manager_BE.converters.ConverterOrder;
import com.example.Restaurant_Manager_BE.dto.DetailsOrderDTO;
import com.example.Restaurant_Manager_BE.entities.*;
import com.example.Restaurant_Manager_BE.dto.OrderDTO;
import com.example.Restaurant_Manager_BE.exceptions.DataNotFoundException;
import com.example.Restaurant_Manager_BE.repositories.*;
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
    private final ModelMapper modelMapper;
    private final TableRepository tableRepository;
    private final LocalizationUtils localizationUtils;
    private final ConverterOrder converterOrder;

    @Override
    public ResponseEntity<APIResponse> createOrder(OrderDTO orderDTO) {
        TableEntity tableEntity = tableRepository.findById(orderDTO.getTableId()).get();
        OrderEntity orderEntity = OrderEntity.builder()
                .dateCreate(orderDTO.getDateCreate())
                .total(orderDTO.getTotal())
                .directionTable(orderDTO.getDirectionTable())
                .isDeleted(false)
                .table(tableEntity)
                .build();
        List<DetailsOrderEntity> detailsOrderEntityList = new ArrayList<>();
        for (DetailsOrderDTO x : orderDTO.getDetailsOrderDTOList()) {
            ProductEntity product = productRepository.findById(x.getProductId()).get();
            DetailsOrderEntity detailsOrder = DetailsOrderEntity.builder()
                    .product(product)
                    .order(orderEntity)
                    .quantity(x.getQuantity())
                    .price(x.getPrice())
                    .isDeleted(false)
                    .build();
            detailsOrderEntityList.add(detailsOrder);
        }
        orderEntity.setDetailsOrderList(detailsOrderEntityList);
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


}
