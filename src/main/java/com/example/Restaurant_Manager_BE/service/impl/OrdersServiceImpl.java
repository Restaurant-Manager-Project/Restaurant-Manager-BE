package com.example.Restaurant_Manager_BE.service.impl;

import com.example.Restaurant_Manager_BE.entity.*;
import com.example.Restaurant_Manager_BE.exception.MessageResponse;
import com.example.Restaurant_Manager_BE.model.DetailsProductModel;
import com.example.Restaurant_Manager_BE.model.OrderModel;
import com.example.Restaurant_Manager_BE.repository.*;
import com.example.Restaurant_Manager_BE.service.OrdersService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductsRepository productsRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private TablesRepository tablesRepository;

    @Override
    public boolean createOrder(OrderModel orderModel) {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setDateCreate(orderModel.getDateCreate());
        orderEntity.setTotal(orderModel.getTotal());
        TableEntity tableEntity = tablesRepository.findById(orderModel.getTableId()).get();
        orderEntity.setTable(tableEntity);
        orderEntity.setDirectionTable(orderModel.getDirectionTable());
        orderEntity.setIsDeleted(false);


        List<DetailsOrderEntity> detailsOrderEntityList = new ArrayList<>();
        for (DetailsProductModel x : orderModel.getDetailsProductModelList()) {
            DetailsOrderEntity detailsOrder = new DetailsOrderEntity();
            ProductEntity product = productsRepository.findById(x.getProductId()).get();
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
    public List<OrderModel> getOrdersByDirection(String direction) {
        List<OrderEntity> orderList = orderRepository.findByDirectionTable(direction);
        List<OrderModel> orderModelList = new ArrayList<>();

        for (OrderEntity x : orderList) {
            OrderModel orderModel = modelMapper.map(x, OrderModel.class);
            orderModelList.add(orderModel);
        }

        return orderModelList;

    }
}
