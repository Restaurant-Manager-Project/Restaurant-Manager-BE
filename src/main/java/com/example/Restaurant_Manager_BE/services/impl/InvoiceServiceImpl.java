package com.example.Restaurant_Manager_BE.services.impl;

import com.example.Restaurant_Manager_BE.constants.MessageKeys;
import com.example.Restaurant_Manager_BE.dto.InvoiceDTO;
import com.example.Restaurant_Manager_BE.entities.ClientEntity;
import com.example.Restaurant_Manager_BE.entities.InvoiceEntity;
import com.example.Restaurant_Manager_BE.entities.RankEntity;
import com.example.Restaurant_Manager_BE.exceptions.DataNotFoundException;
import com.example.Restaurant_Manager_BE.exceptions.InvalidInputException;
import com.example.Restaurant_Manager_BE.repositories.ClientRepository;
import com.example.Restaurant_Manager_BE.repositories.InvoiceRepository;
import com.example.Restaurant_Manager_BE.repositories.RankRepository;
import com.example.Restaurant_Manager_BE.responses.APIResponse;
import com.example.Restaurant_Manager_BE.services.InvoiceService;
import com.example.Restaurant_Manager_BE.utils.LocalizationUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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


    public void updateClientPaid(InvoiceEntity invoiceEntity) {
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
        try {
            invoiceRepository.save(invoiceEntity);
        } catch (Exception e) {
            throw new InvalidInputException(localizationUtils.getLocalizedMessage(MessageKeys.INVOICE_CREATE_FAILED));
        }
        APIResponse apiResponse = new APIResponse();
        apiResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.INVOICE_CREATE_SUCCESS));
        updateClientPaid(invoiceEntity);
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
}
