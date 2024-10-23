package com.example.Restaurant_Manager_BE.controllers;

import com.example.Restaurant_Manager_BE.constants.MessageKeys;
import com.example.Restaurant_Manager_BE.exceptions.InvalidParamException;
import com.example.Restaurant_Manager_BE.responses.APIResponse;
import com.example.Restaurant_Manager_BE.services.TableService;
import com.example.Restaurant_Manager_BE.utils.LocalizationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TableController {
    private final TableService tableService;
    private final LocalizationUtils localizationUtils;
    @GetMapping("/tables")
    public ResponseEntity<APIResponse> selectedTable(@RequestParam String code) {
        if (code.isEmpty()) {
            throw new InvalidParamException(localizationUtils.getLocalizedMessage(MessageKeys.TABLE_NOT_FOUND));
        }
        return tableService.findByDirection(code);
    }

    @GetMapping("/tables/generateQRCode/{id}")
    public ResponseEntity<APIResponse> generateQRCodeLink(@PathVariable Long id) {
        return tableService.generateQRCode(id);
    }

    @GetMapping("/api/table/{direction}/details-orders")
    public ResponseEntity<APIResponse> mergeDetailsOrder(@PathVariable String direction) {
        if (direction.isEmpty()) {
            throw new InvalidParamException(localizationUtils.getLocalizedMessage(MessageKeys.ORDER_NOT_FOUND));
        }
        return tableService.mergeAllDetailsInOrderList(direction);
    }
}
