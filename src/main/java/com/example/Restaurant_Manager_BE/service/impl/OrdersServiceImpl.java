package com.example.Restaurant_Manager_BE.service.impl;

import com.example.Restaurant_Manager_BE.entity.*;
import com.example.Restaurant_Manager_BE.exception.MessageResponse;
import com.example.Restaurant_Manager_BE.model.DetailsProductModel;
import com.example.Restaurant_Manager_BE.model.OrderModel;
import com.example.Restaurant_Manager_BE.repository.*;
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
    public MessageResponse createOrder(OrderModel orderModel) {
        MessageResponse messageRespone = new MessageResponse(404,"Order is null");
        if (orderModel != null) {
            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setDateCreate(orderModel.getDateCreate());
            orderEntity.setTotal(orderModel.getTotal());
            TableEntity tableEntity = tablesRepository.findById(orderModel.getTableId()).get();
            orderEntity.setTable(tableEntity);
            orderEntity.setIsDeleted(false);



            orderRepository.save(orderEntity);



            for (DetailsProductModel x : orderModel.getDetailsProductModelList()) {
                DetailsOrderEntity detailsOrder = new DetailsOrderEntity();
                ProductEntity product = productsRepository.findById(x.getProductId()).get();
                detailsOrder.setProduct(product);
                detailsOrder.setOrder(orderEntity);
                detailsOrder.setQuantity(x.getQuantity());
                detailsOrder.setPrice(x.getPrice());
                detailsOrder.setIsDeleted(false);
                detailsOrderRepository.save(detailsOrder);
            }

            messageRespone = new MessageResponse(200,"Create order success");
        }
        return messageRespone;
    }


    @Override
    public MessageResponse updateOrder(OrderEntity orderEntity) {
        MessageResponse messageRespone = new MessageResponse(404,"Order is null");
        if (orderEntity != null) {

        }
        return messageRespone;
    }
}
