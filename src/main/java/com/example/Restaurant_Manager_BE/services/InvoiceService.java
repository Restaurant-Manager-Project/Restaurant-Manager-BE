package com.example.Restaurant_Manager_BE.services;

import com.example.Restaurant_Manager_BE.dto.InvoiceDTO;
import com.example.Restaurant_Manager_BE.dto.request.InvoiceRequest;
import com.example.Restaurant_Manager_BE.dto.response.InvoiceResponse;
import com.example.Restaurant_Manager_BE.entities.InvoiceEntity;
import com.example.Restaurant_Manager_BE.responses.APIResponse;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

public interface InvoiceService {
    boolean createInvoice(InvoiceRequest invoiceRequest);
    List<InvoiceResponse> findByTimeCreate(Date timeCreate);
    List<InvoiceResponse> getAll();
    boolean deleteInvoice(Long id);
    boolean updateInvoice(Long id, InvoiceRequest invoiceRequest);
//    ResponseEntity<APIResponse> getStatisticRevenue(int year);
}

