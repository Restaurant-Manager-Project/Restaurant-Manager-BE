package com.example.Restaurant_Manager_BE.services;

import com.example.Restaurant_Manager_BE.dto.InvoiceDTO;
import com.example.Restaurant_Manager_BE.entities.InvoiceEntity;
import com.example.Restaurant_Manager_BE.responses.APIResponse;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

public interface InvoiceService {
    ResponseEntity<APIResponse> createInvoice(InvoiceDTO invoiceDTO);
    ResponseEntity<APIResponse> findByTimeCreate(Date timeCreate);
    ResponseEntity<APIResponse> getAll();
    ResponseEntity<APIResponse> deleteInvoice(Long id);
    ResponseEntity<APIResponse> updateInvoice(InvoiceDTO invoiceDTO);
    ResponseEntity<APIResponse> getStatisticRevenue(int year);
}

