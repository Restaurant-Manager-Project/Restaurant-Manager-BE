package com.example.Restaurant_Manager_BE.services.impl;

import com.example.Restaurant_Manager_BE.constants.MessageKeys;
import com.example.Restaurant_Manager_BE.converters.ConverterOrder;
import com.example.Restaurant_Manager_BE.converters.ConverterStatistic;
import com.example.Restaurant_Manager_BE.dto.InvoiceDTO;
import com.example.Restaurant_Manager_BE.dto.OrderDTO;
import com.example.Restaurant_Manager_BE.dto.StatisticDTO.RevenueStatisticDTO;
import com.example.Restaurant_Manager_BE.entities.ClientEntity;
import com.example.Restaurant_Manager_BE.entities.InvoiceEntity;
import com.example.Restaurant_Manager_BE.entities.OrderEntity;
import com.example.Restaurant_Manager_BE.entities.RankEntity;
import com.example.Restaurant_Manager_BE.enums.StatusOrder;
import com.example.Restaurant_Manager_BE.exceptions.DataNotFoundException;
import com.example.Restaurant_Manager_BE.exceptions.InvalidInputException;
import com.example.Restaurant_Manager_BE.repositories.ClientRepository;
import com.example.Restaurant_Manager_BE.repositories.InvoiceRepository;
import com.example.Restaurant_Manager_BE.repositories.OrderRepository;
import com.example.Restaurant_Manager_BE.repositories.RankRepository;
import com.example.Restaurant_Manager_BE.responses.APIResponse;
import com.example.Restaurant_Manager_BE.services.InvoiceService;
import com.example.Restaurant_Manager_BE.services.TableService;
import com.example.Restaurant_Manager_BE.utils.LocalizationUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final ModelMapper modelMapper;
    private final LocalizationUtils localizationUtils;
    private final ClientRepository ClientRepository;
    private final ClientRepository clientRepository;
    private final RankRepository rankRepository;
    private final ConverterStatistic converterStatistic;
    private final OrderRepository orderRepository;
    private final ConverterOrder converterOrder;
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
    public ResponseEntity<APIResponse> createInvoice(InvoiceDTO invoiceDTO) {
        if (invoiceDTO == null || invoiceDTO.getTotal() == null || invoiceDTO.getTimeCreate() == null) {
            throw new InvalidInputException(localizationUtils.getLocalizedMessage(MessageKeys.INVALID_INPUT));
        }
        InvoiceEntity invoiceEntity = modelMapper.map(invoiceDTO, InvoiceEntity.class);
        invoiceEntity.setIsDeleted(false);

        List<OrderDTO> ordersListDTO = invoiceDTO.getOrderDTOList();
        if(ordersListDTO == null || ordersListDTO.size() == 0) {
            throw new InvalidInputException(localizationUtils.getLocalizedMessage(MessageKeys.INVALID_INPUT));
        }
        try {
            invoiceRepository.save(invoiceEntity);
        } catch (Exception e) {
            throw new InvalidInputException(localizationUtils.getLocalizedMessage(MessageKeys.INVOICE_CREATE_FAILED));
        }
        String direction = "";
        Long tableId = 0L;
        for(OrderDTO orderDTO : ordersListDTO) {
            OrderEntity orderEntity = orderRepository.findById(orderDTO.getOrderId())
                    .orElseThrow(()->new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKeys.ORDER_NOT_FOUND)));
            direction = orderEntity.getDirectionTable();
            orderEntity.setInvoice(invoiceEntity);
            tableId = orderEntity.getTable().getId();
            orderRepository.save(orderEntity);
        }
        tableService.generateDirection(direction);
        tableService.updateStatusOfTableByID(tableId, StatusOrder.SERVED.getId());
        APIResponse apiResponse = new APIResponse();
        updateClientPaid(invoiceEntity);
        apiResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.INVOICE_CREATE_SUCCESS));
        apiResponse.setResult(invoiceEntity);
        return ResponseEntity.ok(apiResponse);
    }

    @Override
    public ResponseEntity<APIResponse> updateInvoice(InvoiceDTO invoiceDTO) {
        if (invoiceDTO == null || invoiceDTO.getTotal() == null || invoiceDTO.getTimeCreate() == null) {
            throw new InvalidInputException(localizationUtils.getLocalizedMessage(MessageKeys.INVALID_INPUT));
        }
        InvoiceEntity invoiceEntity = invoiceRepository.findById(invoiceDTO.getId())
                .orElseThrow(() -> new DataNotFoundException(
                        localizationUtils.getLocalizedMessage(MessageKeys.INVOICE_NOT_EXISTED)));
        invoiceEntity.setTotal(invoiceDTO.getTotal());
        invoiceEntity.setTimeCreate(invoiceDTO.getTimeCreate());
        try {
            invoiceRepository.save(invoiceEntity);
        } catch (Exception e) {
            throw new InvalidInputException(localizationUtils.getLocalizedMessage(MessageKeys.INVOICE_CREATE_FAILED));
        }
        APIResponse apiResponse = new APIResponse();
        apiResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.INVOICE_UPDATE_SUCCESS));
        apiResponse.setResult(invoiceEntity);
        return ResponseEntity.ok(apiResponse);
    }

    @Override
    public ResponseEntity<APIResponse> deleteInvoice(Long id) {
        Optional<InvoiceEntity> invoiceOptional = invoiceRepository.findById(id);
        if (invoiceOptional.isPresent()) {
            InvoiceEntity invoiceEntity = invoiceOptional.get();
            invoiceEntity.setIsDeleted(true);
            invoiceRepository.save(invoiceEntity);
            APIResponse apiResponse = new APIResponse();
            apiResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.INVOICE_DELETE_SUCCESS));
            apiResponse.setResult(invoiceEntity);
            return ResponseEntity.ok(apiResponse);
        } else {
            APIResponse apiResponse = new APIResponse();
            apiResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.INVOICE_DELETE_FAILED));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }
    }

    @Override
    public ResponseEntity<APIResponse> getAll() {
        List<InvoiceEntity> invoiceEntityList = invoiceRepository.findAll();
        List<InvoiceDTO> invoiceDTOList = invoiceEntityList.stream()
                .map(entity -> modelMapper.map(entity, InvoiceDTO.class))
                .collect(Collectors.toList());
        APIResponse apiResponse = new APIResponse();
        apiResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.INVOICE_LIST_GET_SUCCESS));
        apiResponse.setResult(invoiceDTOList);

        return ResponseEntity.ok(apiResponse);
    }

    @Override
    public ResponseEntity<APIResponse> findByTimeCreate(Date timeCreate) {
        InvoiceEntity invoiceEntity = invoiceRepository.findByTimeCreate(timeCreate)
                .orElseThrow(() -> new DataNotFoundException(
                        localizationUtils.getLocalizedMessage(MessageKeys.INVOICE_NOT_EXISTED)));
        InvoiceDTO invoiceDTO = modelMapper.map(invoiceEntity, InvoiceDTO.class);
        APIResponse APIResponse = new APIResponse();
        APIResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.INVOICE_GET_SUCCESS));
        APIResponse.setResult(invoiceDTO);
        return ResponseEntity.ok(APIResponse);
    }

    @Override
    public ResponseEntity<APIResponse> getStatisticRevenue(int year) {
        List<Object[]> invoiceEntity=invoiceRepository.RevenueStatisticByYear(year);
        List<RevenueStatisticDTO> result = converterStatistic.RevenueStatisticDTO_List(invoiceEntity);
        APIResponse APIResponse = new APIResponse();
        APIResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.INVOICE_GET_SUCCESS));
        APIResponse.setResult(result);
        return ResponseEntity.ok(APIResponse);
    }
}
