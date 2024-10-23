package com.example.Restaurant_Manager_BE.services.impl;

import com.example.Restaurant_Manager_BE.dto.DetailsOrderDTO;
import com.example.Restaurant_Manager_BE.repositories.DetailsOrderRepository;
import com.example.Restaurant_Manager_BE.services.DetailsOrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DetailsOrderServiceImpl implements DetailsOrderService {

    private final DetailsOrderRepository detailsOrderRepository;
    private final ModelMapper modelMapper;
    @Override
    public List<DetailsOrderDTO> mergeDetailsOrder(List<Long> listOrderID) {
//        List<DetailsOrderEntity> detailsOrderEntityList = detailsOrderRepository.mergeDetailsOrder(listOrderID);
        List<Map<String, Object>> test = detailsOrderRepository.mergeDetailsOrder(listOrderID);
        List<DetailsOrderDTO> detailsOrderDTOList = new ArrayList<>();
        test.forEach((Map<String, Object> map) -> {
            DetailsOrderDTO detailsOrderDTO = new DetailsOrderDTO();
            detailsOrderDTO.setProductId((Long) map.get("product_id"));
            detailsOrderDTO.setProductName((String) map.get("name"));
            detailsOrderDTO.setQuantity((Integer) map.get("quantity"));
            detailsOrderDTO.setPrice((Long) map.get("price"));
            detailsOrderDTOList.add(detailsOrderDTO);

        });
        System.out.println("zxczxc");
        return detailsOrderDTOList;
    }
}
