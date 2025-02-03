package com.example.Restaurant_Manager_BE.services.impl;
import com.example.Restaurant_Manager_BE.constants.MessageKeys;
import com.example.Restaurant_Manager_BE.dto.DetailsOrderDTO;
import com.example.Restaurant_Manager_BE.dto.ProductDTO;
import com.example.Restaurant_Manager_BE.entities.OrderEntity;
import com.example.Restaurant_Manager_BE.entities.TableEntity;
import com.example.Restaurant_Manager_BE.exceptions.DataNotFoundException;
import com.example.Restaurant_Manager_BE.mapper.TableMapper;
import com.example.Restaurant_Manager_BE.dto.TableDTO;
import com.example.Restaurant_Manager_BE.repositories.DetailsOrderRepository;
import com.example.Restaurant_Manager_BE.repositories.OrderRepository;
import com.example.Restaurant_Manager_BE.repositories.TableRepository;
import com.example.Restaurant_Manager_BE.responses.APIResponse;
import com.example.Restaurant_Manager_BE.services.TableService;
import com.example.Restaurant_Manager_BE.utils.LocalizationUtils;
import com.example.Restaurant_Manager_BE.utils.QRcode;

import jakarta.persistence.Table;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.Restaurant_Manager_BE.repositories.StatusTableRepository;
import com.example.Restaurant_Manager_BE.entities.StatusTableEntity;
import com.example.Restaurant_Manager_BE.dto.request.TableRequest;
import com.example.Restaurant_Manager_BE.dto.response.StatusTableResponse;
import com.example.Restaurant_Manager_BE.dto.response.TableResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class TableServiceImpl implements TableService {
    private final StatusTableRepository statusTableRepository;
    private final TableRepository tableRepository;
    private final DetailsOrderRepository detailsOrderRepository;
    private final OrderRepository orderRepository;
//    private final ModelMapper modelMapper;
    private final LocalizationUtils localizationUtils;
    private final TableMapper tableMapper;
    private final QRcode qrCode;


    @Override
    public TableResponse findByDirection(String direction) {
//        TableEntity tableEntity = tableRepository.findByDirection(direction)
//                .orElseThrow(() -> new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKeys.TABLE_NOT_FOUND)));
//        TableDTO tableModel = modelMapper.map(tableEntity, TableDTO.class);
//        tableModel.setStatusName(tableEntity.getStatusTable().getName());
//        APIResponse APIResponse = new APIResponse();
//        APIResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.TABLE_GET_SUCCESS));
//        APIResponse.setResult(tableModel);
//        return ResponseEntity.ok(APIResponse);
        return null;
    }

    @Override
    public void generateDirection(String direction) {
        TableEntity tableEntity = tableRepository.findByDirection(direction)
                .orElseThrow(() -> new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKeys.TABLE_NOT_FOUND)));
        String directionNew = qrCode.generatePassword(25);
        tableEntity.setDirection(directionNew);
        tableRepository.save(tableEntity);
    }

    @Override
    public String generateQRCode(Long tableId) {
        TableEntity tableEntity = tableRepository.findById(tableId)
                .orElseThrow(() -> new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKeys.TABLE_NOT_FOUND)));
        String linkQR = qrCode.generateQR(tableEntity.getDirection());
        return linkQR;
    }

    // @Override
    // public TableResponse mergeAllDetailsInOrderList(String direction) {
    //     List<OrderEntity> orderList = orderRepository.findByDirectionTable(direction)
    //             .filter(orderEntities -> !orderEntities.isEmpty())
    //             .orElseThrow(() -> new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKeys.ORDER_NOT_FOUND)));
    //     List<Long> listOrderId = new ArrayList<>();
    //     orderList.forEach(order -> {
    //         listOrderId.add(order.getId());
    //     });
    //     List<Map<String, Object>> test = detailsOrderRepository.mergeDetailsOrder(listOrderId);
    //     List<DetailsOrderDTO> detailsOrderDTOList = new ArrayList<>();
    //     test.forEach((Map<String, Object> map) -> {
    //         DetailsOrderDTO detailsOrderDTO = new DetailsOrderDTO();
    //         detailsOrderDTO.setProductId((Long) map.get("product_id"));
    //         detailsOrderDTO.setProductName((String) map.get("name"));
    //         detailsOrderDTO.setQuantity((Integer) map.get("quantity"));
    //         detailsOrderDTO.setPrice((Long) map.get("price"));
    //         detailsOrderDTOList.add(detailsOrderDTO);
    //     });
    //     APIResponse APIResponse = new APIResponse();
    //     APIResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.DETAILS_ORDER_CREATE_SUCCESS));
    //     APIResponse.setResult(detailsOrderDTOList);
    //     return ResponseEntity.ok(APIResponse);
    // }
    @Override
    public TableResponse updateStatusOfTableByName(Long id, String status_name){
        TableEntity tableEntity = tableRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKeys.TABLE_NOT_FOUND)));
        StatusTableEntity statusTableEntity= statusTableRepository.findByName(status_name)
                        .orElseThrow(()->new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKeys.STATUS_TABLE_NOT_FOUND)));
        
        tableEntity.setStatusTable(statusTableEntity);
        TableEntity result = tableRepository.save(tableEntity);
        return TableResponse.builder()
            .id(result.getId())
            .direction(result.getDirection())
            .name(result.getName())
            .statusName(result.getStatusTable().getName())
        .build();
    }

    @Override
    public TableResponse deleteTableByID(Long id){
        TableEntity tableEntity = tableRepository.findById(id)
                .orElseThrow(()->new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKeys.TABLE_NOT_FOUND)));
        tableEntity.setIsDeleted(true);
        TableEntity result = tableRepository.save(tableEntity);
        return TableResponse.builder()
            .id(result.getId())
            .direction(result.getDirection())
            .name(result.getName())
            .statusName(result.getStatusTable().getName())
        .build();
    }
    @Override
    public List<TableResponse> getALLTables(){
       List<TableEntity> tableEntityList = tableRepository.findAllWithStatusTable();
       System.out.println(tableEntityList.get(0).getStatusTable().getName());
       List<TableResponse> tableResponsesList = tableMapper.toListDto(tableEntityList);
       
        return tableResponsesList;
    }
}
