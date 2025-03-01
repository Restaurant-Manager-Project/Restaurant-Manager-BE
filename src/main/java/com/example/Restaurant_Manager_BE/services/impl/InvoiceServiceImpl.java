package com.example.Restaurant_Manager_BE.services.impl;

import com.example.Restaurant_Manager_BE.constants.MessageKeys;
import com.example.Restaurant_Manager_BE.converters.ConverterStatistic;
import com.example.Restaurant_Manager_BE.dto.request.InvoiceRequest;
import com.example.Restaurant_Manager_BE.dto.response.InvoiceResponse;
import com.example.Restaurant_Manager_BE.entities.ClientEntity;
import com.example.Restaurant_Manager_BE.entities.InvoiceEntity;
import com.example.Restaurant_Manager_BE.entities.OrderEntity;
import com.example.Restaurant_Manager_BE.entities.RankEntity;
import com.example.Restaurant_Manager_BE.exceptions.DataNotFoundException;
import com.example.Restaurant_Manager_BE.mapper.request.InvoiceRequestMapper;
import com.example.Restaurant_Manager_BE.mapper.response.InvoiceResponseMapper;
import com.example.Restaurant_Manager_BE.repositories.ClientRepository;
import com.example.Restaurant_Manager_BE.repositories.InvoiceRepository;
import com.example.Restaurant_Manager_BE.repositories.OrderRepository;
import com.example.Restaurant_Manager_BE.repositories.RankRepository;
import com.example.Restaurant_Manager_BE.services.InvoiceService;
import com.example.Restaurant_Manager_BE.services.TableService;
import com.example.Restaurant_Manager_BE.utils.LocalizationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final LocalizationUtils localizationUtils;
    private final ClientRepository ClientRepository;
    private final ClientRepository clientRepository;
    private final RankRepository rankRepository;
    private final ConverterStatistic converterStatistic;
    private final OrderRepository orderRepository;
    private final InvoiceRequestMapper invoiceRequestMapper;
    private final InvoiceResponseMapper invoiceResponseMapper;

    private final TableService tableService;


    public void updateClientPaid(InvoiceEntity invoiceEntity) {
        if (invoiceEntity.getClient() == null) {
            return;
        }
        ClientEntity client = clientRepository.findById(invoiceEntity.getClient().getId())
                .orElseThrow(()->new DataNotFoundException
                        (localizationUtils.getLocalizedMessage(MessageKeys.CLIENT_NOT_EXISTS)));
        List<InvoiceEntity> invoices = invoiceRepository.findByClient(client);
        Long updatedPaid = invoices.stream()
                .mapToLong(InvoiceEntity::getTotal)
                .sum();
        client.setPaid(updatedPaid);
        List<RankEntity> AllRank = rankRepository.findAll();
        client.updateRank(AllRank);
        clientRepository.save(client);
    }

    @Override
    @Transactional
    public InvoiceResponse createInvoice(InvoiceRequest invoiceRequest) {
        InvoiceEntity invoiceEntity = invoiceRequestMapper.toEntity(invoiceRequest);
        ClientEntity clientEntity = null;
        if (invoiceRequest.getClientId() != null) {
            clientEntity = clientRepository.findById(invoiceRequest.getClientId())
                    .orElseThrow(() -> new DataNotFoundException(
                            localizationUtils.getLocalizedMessage(MessageKeys.CLIENT_NOT_EXISTS)));
        }
        List<OrderEntity> orderEntityList = orderRepository.getAllOrderWithDetailsByDirectionTable(invoiceRequest.getDirection());
        for (OrderEntity orderEntity : orderEntityList) {
            orderEntity.setInvoice(invoiceEntity);
        }
        invoiceEntity.setClient(clientEntity);
        invoiceEntity.setOrderEntityList(orderEntityList);
        invoiceRepository.save(invoiceEntity);
        return invoiceResponseMapper.toDto(invoiceEntity);
//        List<OrderDTO> ordersListDTO = invoiceDTO.getOrderDTOList();
//        if(ordersListDTO == null || ordersListDTO.size() == 0) {
//            throw new InvalidInputException(localizationUtils.getLocalizedMessage(MessageKeys.INVALID_INPUT));
//        }
//        try {
//            invoiceRepository.save(invoiceEntity);
//        } catch (Exception e) {
//            throw new InvalidInputException(localizationUtils.getLocalizedMessage(MessageKeys.INVOICE_CREATE_FAILED));
//        }
//        String direction = "";
//        Long tableId = 0L;
//        for(OrderDTO orderDTO : ordersListDTO) {
//            OrderEntity orderEntity = orderRepository.findById(orderDTO.getOrderId())
//                    .orElseThrow(()->new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKeys.ORDER_NOT_FOUND)));
//            direction = orderEntity.getDirectionTable();
//            orderEntity.setInvoice(invoiceEntity);
//            tableId = orderEntity.getTable().getId();
//            orderRepository.save(orderEntity);
//        }
//
//        tableService.generateDirection(direction);
//        tableService.updateStatusOfTableByID(tableId, StatusTable.AVAILABLE.getId());
//
//
//
//        if (invoiceEntity.getClient() != null) {
//            ClientEntity client = clientRepository.findById(invoiceEntity.getClient().getId()).orElse(null);
//            client.setPaid(client.getPaid() + invoiceEntity.getTotal());
//            List<RankEntity> AllRank = rankRepository.findAll();
//            client.updateRank(AllRank);
//            ClientRepository.save(client);
//        }
//
//
//        APIResponse apiResponse = new APIResponse();
//        updateClientPaid(invoiceEntity);
//        apiResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.INVOICE_CREATE_SUCCESS));
//        apiResponse.setResult(invoiceEntity);
//        return ResponseEntity.ok(apiResponse);

    }

    @Override
    public boolean updateInvoice(Long id, InvoiceRequest invoiceRequest) {
//        if (invoiceDTO == null || invoiceDTO.getTotal() == null || invoiceDTO.getTimeCreate() == null) {
//            throw new InvalidInputException(localizationUtils.getLocalizedMessage(MessageKeys.INVALID_INPUT));
//        }
//        InvoiceEntity invoiceEntity = invoiceRepository.findById(invoiceDTO.getId())
//                .orElseThrow(() -> new DataNotFoundException(
//                        localizationUtils.getLocalizedMessage(MessageKeys.INVOICE_NOT_EXISTED)));
//        invoiceEntity.setTotal(invoiceDTO.getTotal());
//        invoiceEntity.setTimeCreate(invoiceDTO.getTimeCreate());
//        try {
//            invoiceRepository.save(invoiceEntity);
//        } catch (Exception e) {
//            throw new InvalidInputException(localizationUtils.getLocalizedMessage(MessageKeys.INVOICE_CREATE_FAILED));
//        }
//        APIResponse apiResponse = new APIResponse();
//        apiResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.INVOICE_UPDATE_SUCCESS));
//        apiResponse.setResult(invoiceEntity);
//        return ResponseEntity.ok(apiResponse);
        return false;
    }

    @Override
    public boolean deleteInvoice(Long id) {
//        Optional<InvoiceEntity> invoiceOptional = invoiceRepository.findById(id);
//        if (invoiceOptional.isPresent()) {
//            InvoiceEntity invoiceEntity = invoiceOptional.get();
//            invoiceEntity.setIsDeleted(true);
//            invoiceRepository.save(invoiceEntity);
//            APIResponse apiResponse = new APIResponse();
//            apiResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.INVOICE_DELETE_SUCCESS));
//            apiResponse.setResult(invoiceEntity);
//            return ResponseEntity.ok(apiResponse);
//        } else {
//            APIResponse apiResponse = new APIResponse();
//            apiResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.INVOICE_DELETE_FAILED));
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
//        }
        return true;
    }

    @Override
    public List<InvoiceResponse> getAll() {
        List<InvoiceEntity> invoiceEntityList = invoiceRepository.findAll();
        return invoiceResponseMapper.toListDto(invoiceEntityList);
    }

    @Override
    public List<InvoiceResponse> findByTimeCreate(Date timeCreate) {
//        InvoiceEntity invoiceEntity = invoiceRepository.findByTimeCreate(timeCreate)
//                .orElseThrow(() -> new DataNotFoundException(
//                        localizationUtils.getLocalizedMessage(MessageKeys.INVOICE_NOT_EXISTED)));
//        InvoiceDTO invoiceDTO = modelMapper.map(invoiceEntity, InvoiceDTO.class);
//        APIResponse APIResponse = new APIResponse();
//        APIResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.INVOICE_GET_SUCCESS));
//        APIResponse.setResult(invoiceDTO);
//        return ResponseEntity.ok(APIResponse);
        return null;
    }

//    @Override
//    public ResponseEntity<APIResponse> getStatisticRevenue(int year) {
//        List<Object[]> invoiceEntity=invoiceRepository.RevenueStatisticByYear(year);
//        List<RevenueStatisticDTO> result = converterStatistic.RevenueStatisticDTO_List(invoiceEntity);
//        APIResponse APIResponse = new APIResponse();
//        APIResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.INVOICE_GET_SUCCESS));
//        APIResponse.setResult(result);
//        return ResponseEntity.ok(APIResponse);
//    }
}
