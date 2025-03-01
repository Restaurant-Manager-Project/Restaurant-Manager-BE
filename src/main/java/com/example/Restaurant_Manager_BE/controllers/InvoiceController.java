package com.example.Restaurant_Manager_BE.controllers;

import com.example.Restaurant_Manager_BE.constants.MessageKeys;
import com.example.Restaurant_Manager_BE.dto.request.InvoiceRequest;
import com.example.Restaurant_Manager_BE.responses.APIResponse;
import com.example.Restaurant_Manager_BE.services.InvoiceService;
import com.example.Restaurant_Manager_BE.utils.LocalizationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;
    private final LocalizationUtils localizationUtils;

    @PreAuthorize("hasAuthority('invoice.create')")
    @PostMapping("/api/invoices")
    public ResponseEntity<APIResponse> createInvoice(@RequestBody InvoiceRequest invoiceRequest) {

        String message = invoiceService.createInvoice(invoiceRequest) != null ?
                localizationUtils.getLocalizedMessage(MessageKeys.INVOICE_CREATE_SUCCESS) :
                localizationUtils.getLocalizedMessage(MessageKeys.INVOICE_CREATE_FAILED);
        return ResponseEntity.ok(APIResponse.builder()
                .message(message)
                .build());
    }

    @PreAuthorize("hasAuthority('invoice.update')")
    @PutMapping("/api/invoices/{id}")
    public ResponseEntity<APIResponse> updateInvoice(@PathVariable Long id, @RequestBody InvoiceRequest invoiceRequest) {
        String message = invoiceService.updateInvoice(id, invoiceRequest) ?
                localizationUtils.getLocalizedMessage(MessageKeys.INVOICE_UPDATE_SUCCESS) :
                localizationUtils.getLocalizedMessage(MessageKeys.INVOICE_UPDATE_FAILED);
        return ResponseEntity.ok(APIResponse.builder()
                .message(message)
                .build());
    }

    @DeleteMapping("/api/invoices/{id}")
    public ResponseEntity<APIResponse> deleteInvoice(@PathVariable Long id) {
        String message = invoiceService.deleteInvoice(id) ?
                localizationUtils.getLocalizedMessage(MessageKeys.INVOICE_DELETE_SUCCESS) :
                localizationUtils.getLocalizedMessage(MessageKeys.INVOICE_DELETE_FAILED);
        return ResponseEntity.ok(APIResponse.builder()
                .message(message)
                .build());
    }

    @PreAuthorize("hasAuthority('invoice.view')")
    @GetMapping("/api/invoices")
    public ResponseEntity<APIResponse> getAllInvoices() {
        return ResponseEntity.ok(APIResponse.builder()
                .message(localizationUtils.getLocalizedMessage(MessageKeys.INVOICE_LIST_GET_SUCCESS))
                .result(invoiceService.getAll())
                .build());
    }

    @PreAuthorize("hasAuthority('invoice.view')")
    @GetMapping("/api/invoices/search")
    public ResponseEntity<APIResponse> findByTimeCreate(@RequestParam("timeCreate") Date timeCreate) {
        return ResponseEntity.ok(APIResponse.builder()
                .message(localizationUtils.getLocalizedMessage(MessageKeys.INVOICE_LIST_GET_SUCCESS))
                .result(invoiceService.findByTimeCreate(timeCreate))
                .build());
    }
}
