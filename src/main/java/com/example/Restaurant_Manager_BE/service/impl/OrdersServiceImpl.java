package com.example.Restaurant_Manager_BE.service.impl;

import com.example.Restaurant_Manager_BE.entity.ClientEntity;
import com.example.Restaurant_Manager_BE.entity.DetailsOrderEntity;
import com.example.Restaurant_Manager_BE.entity.OrderEntity;
import com.example.Restaurant_Manager_BE.entity.ProductEntity;
import com.example.Restaurant_Manager_BE.exception.MessageRespone;
import com.example.Restaurant_Manager_BE.model.DetailsProductModel;
import com.example.Restaurant_Manager_BE.model.OrderModel;
import com.example.Restaurant_Manager_BE.repository.ClientRepository;
import com.example.Restaurant_Manager_BE.repository.DetailsOrderRepository;
import com.example.Restaurant_Manager_BE.repository.OrderRepository;
import com.example.Restaurant_Manager_BE.repository.ProductsRepository;
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

    @Override
    public MessageRespone createOrder(OrderModel orderModel) {
        MessageRespone messageRespone = new MessageRespone(404,"Order is null");
        if (orderModel != null) {
            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setDate_create(orderModel.getDate_create());
            orderEntity.setTotal(orderModel.getTotal());
            orderEntity.setIs_deleted(false);

            Long client_id = Long.parseLong(orderModel.getClient_id());
            ClientEntity clientEntity = clientRepository.findById(client_id).get();
            orderEntity.setClient(clientEntity);

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
