package com.example.Restaurant_Manager_BE.service.impl;

import com.example.Restaurant_Manager_BE.entity.DetailsOrderEntity;
import com.example.Restaurant_Manager_BE.entity.ProductEntity;
import com.example.Restaurant_Manager_BE.model.DetailsOrderModel;
import com.example.Restaurant_Manager_BE.repository.DetailsOrderRepository;
import com.example.Restaurant_Manager_BE.service.DetailsOrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DetailsOrderServiceImpl implements DetailsOrderService {
    @Autowired
    private DetailsOrderRepository detailsOrderRepository;
    private ModelMapper modelMapper = new ModelMapper();
    @Override
    public List<DetailsOrderModel> mergeDetailsOrder(List<Long> listOrderID) {
//        List<DetailsOrderEntity> detailsOrderEntityList = detailsOrderRepository.mergeDetailsOrder(listOrderID);
        List<Map<String, Object>> test = detailsOrderRepository.mergeDetailsOrder(listOrderID);
        List<DetailsOrderModel> detailsOrderModelList = new ArrayList<>();
        test.forEach((Map<String, Object> map) -> {
            DetailsOrderModel detailsOrderModel = new DetailsOrderModel();
            detailsOrderModel.setProductId((Long) map.get("product_id"));
            detailsOrderModel.setProductName((String) map.get("name"));
            detailsOrderModel.setQuantity((Integer) map.get("quantity"));
            detailsOrderModel.setPrice((Long) map.get("price"));
            detailsOrderModelList.add(detailsOrderModel);

        });
        System.out.println("zxczxc");
        return detailsOrderModelList;
    }
}
