package com.example.Restaurant_Manager_BE.services.impl;

import com.example.Restaurant_Manager_BE.entities.*;
import com.example.Restaurant_Manager_BE.dto.DetailsProductDTO;
import com.example.Restaurant_Manager_BE.dto.OrderDTO;
import com.example.Restaurant_Manager_BE.repositories.*;
import com.example.Restaurant_Manager_BE.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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

    @Override
    public boolean createOrder(OrderDTO orderDTO) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setDateCreate(orderDTO.getDateCreate());
        orderEntity.setTotal(orderDTO.getTotal());
        TableEntity tableEntity = tableRepository.findById(orderDTO.getTableId()).get();
        orderEntity.setTable(tableEntity);
        orderEntity.setDirectionTable(orderDTO.getDirectionTable());
        orderEntity.setIsDeleted(false);


        List<DetailsOrderEntity> detailsOrderEntityList = new ArrayList<>();
        for (DetailsProductDTO x : orderDTO.getDetailsProductDTOList()) {
            DetailsOrderEntity detailsOrder = new DetailsOrderEntity();
            ProductEntity product = productRepository.findById(x.getProductId()).get();
            detailsOrder.setProduct(product);
            detailsOrder.setOrder(orderEntity);
            detailsOrder.setQuantity(x.getQuantity());
            detailsOrder.setPrice(x.getPrice());
            detailsOrder.setIsDeleted(false);
            detailsOrderEntityList.add(detailsOrder);
        }
        orderEntity.setDetailsOrderList(detailsOrderEntityList);
        orderRepository.save(orderEntity);
        return true;
    }

    @Override
    public List<OrderDTO> getOrdersByDirection(String direction) {
        List<OrderEntity> orderList = orderRepository.findByDirectionTable(direction);
        List<OrderDTO> orderDTOList = new ArrayList<>();

        for (OrderEntity x : orderList) {
            OrderDTO orderDTO = modelMapper.map(x, OrderDTO.class);
            orderDTOList.add(orderDTO);
        }

        return orderDTOList;

    }
}
