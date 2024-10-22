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

@Service
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductsRepository productsRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private DetailsOrderRepository detailsOrderRepository;

    @Autowired
    private TablesRepository tablesRepository;

    private ModelMapper modelMapper = new ModelMapper();
    @Override
    public boolean createOrder(OrderModel orderModel) {
//        OrderEntity orderEntity = new OrderEntity();
//        orderEntity.setDateCreate(orderModel.getDateCreate());
//        orderEntity.setTotal(orderModel.getTotal());
//        TableEntity tableEntity = tablesRepository.findById(orderModel.getTableId()).get();
//        orderEntity.setTable(tableEntity);
//        orderEntity.setIsDeleted(false);
//
////        orderRepository.save(orderEntity);
//
//        for (DetailsProductModel x : orderModel.getDetailsProductModelList()) {
//            DetailsOrderEntity detailsOrder = new DetailsOrderEntity();
//            ProductEntity product = productsRepository.findById(x.getProductId()).get();
//            detailsOrder.setProduct(product);
//            detailsOrder.setOrder(orderEntity);
//            detailsOrder.setQuantity(x.getQuantity());
//            detailsOrder.setPrice(x.getPrice());
//            detailsOrder.setIsDeleted(false);
//            detailsOrderRepository.save(detailsOrder);
//        }
        OrderEntity orderEntity = modelMapper.map(orderModel, OrderEntity.class);
        System.out.println("cccc");


        return true;
    }


    @Override
    public MessageResponse updateOrder(OrderEntity orderEntity) {
//        MessageResponse messageRespone = new MessageResponse(404,"Order is null");
//        if (orderEntity != null) {
//
//        }
//        return messageRespone;
        return null;
    }
}
