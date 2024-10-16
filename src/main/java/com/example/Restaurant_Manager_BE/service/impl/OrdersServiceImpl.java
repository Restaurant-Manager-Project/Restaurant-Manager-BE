package com.example.Restaurant_Manager_BE.service.impl;

import com.example.Restaurant_Manager_BE.entity.*;
import com.example.Restaurant_Manager_BE.exception.MessageRespone;
import com.example.Restaurant_Manager_BE.model.DetailsProductModel;
import com.example.Restaurant_Manager_BE.model.OrderModel;
import com.example.Restaurant_Manager_BE.repository.*;
import com.example.Restaurant_Manager_BE.service.DetailsOrderService;
import com.example.Restaurant_Manager_BE.service.OrdersService;
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
    @Override
    public MessageRespone createOrder(OrderModel orderModel) {
        MessageRespone messageRespone = new MessageRespone(404,"Order is null");
        if (orderModel != null) {
            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setDate_create(orderModel.getDate_create());
            orderEntity.setTotal(orderModel.getTotal());
            TableEntity tableEntity = tablesRepository.findById(orderModel.getTable_id()).get();
            orderEntity.setTable(tableEntity);
            orderEntity.setIs_deleted(false);



            orderRepository.save(orderEntity);



            for (DetailsProductModel x : orderModel.getDetailsProductModelList()) {
                DetailsOrderEntity detailsOrder = new DetailsOrderEntity();
                ProductEntity product = productsRepository.findById(x.getProduct_id()).get();
                detailsOrder.setProduct(product);
                detailsOrder.setOrder(orderEntity);
                detailsOrder.setQuantity(x.getQuantity());
                detailsOrder.setPrice(x.getPrice());
                detailsOrder.setIs_deleted(false);
                detailsOrderRepository.save(detailsOrder);
            }

            messageRespone = new MessageRespone(200,"Create order success");
        }
        return messageRespone;
    }


    @Override
    public MessageRespone updateOrder(OrderEntity orderEntity) {
        MessageRespone messageRespone = new MessageRespone(404,"Order is null");
        if (orderEntity != null) {

        }
        return messageRespone;
    }
}
