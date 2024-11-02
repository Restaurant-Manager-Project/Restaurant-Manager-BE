package com.example.Restaurant_Manager_BE.controllers;

import com.example.Restaurant_Manager_BE.dto.InvoiceDTO;
import com.example.Restaurant_Manager_BE.responses.APIResponse;
import com.example.Restaurant_Manager_BE.services.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;

    @PostMapping("/api/invoices")
    public ResponseEntity<APIResponse> createInvoice(@RequestBody InvoiceDTO invoiceDTO) {
        return invoiceService.createInvoice(invoiceDTO);
    }

    @PutMapping("/api/invoices")
    public ResponseEntity<APIResponse> updateInvoice(@RequestBody InvoiceDTO invoiceDTO) {
        return invoiceService.updateInvoice(invoiceDTO);
    }

    @DeleteMapping("/api/invoices/{id}")
    public ResponseEntity<APIResponse> deleteInvoice(@PathVariable Long id) {
        return invoiceService.deleteInvoice(id);
    }

    @GetMapping("/api/invoices")
    public ResponseEntity<APIResponse> getAllInvoices() {
        return invoiceService.getAll();
    }

    @GetMapping("/api/invoices/search")
    public ResponseEntity<APIResponse> findByTimeCreate(@RequestParam("timeCreate") Date timeCreate) {
        return invoiceService.findByTimeCreate(timeCreate);
    }
}
