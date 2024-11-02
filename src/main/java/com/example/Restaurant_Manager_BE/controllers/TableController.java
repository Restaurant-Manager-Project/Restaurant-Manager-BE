package com.example.Restaurant_Manager_BE.controllers;

import com.example.Restaurant_Manager_BE.constants.MessageKeys;
import com.example.Restaurant_Manager_BE.exceptions.InvalidParamException;
import com.example.Restaurant_Manager_BE.responses.APIResponse;
import com.example.Restaurant_Manager_BE.services.TableService;
import com.example.Restaurant_Manager_BE.utils.LocalizationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Chỉnh sửa trạng thái của bàn")
    @PutMapping("/api/table/{table_id}/status")
    public ResponseEntity<APIResponse> updateStatusOfTable(@PathVariable Long table_id, @RequestParam("statusID") Long statusID) {
        System.out.println(table_id+" "+statusID);
        return tableService.updateStatusOfTableByID(table_id,statusID);
    }
    @GetMapping("/api/table/{direction}/details-orders")
    public ResponseEntity<APIResponse> mergeDetailsOrder(@PathVariable String direction) {
        if (direction.isEmpty()) {
            throw new InvalidParamException(localizationUtils.getLocalizedMessage(MessageKeys.ORDER_NOT_FOUND));
        }
        return tableService.mergeAllDetailsInOrderList(direction);
    }
    @Operation(summary = "Xóa table")
    @DeleteMapping("/api/table/{table_id}")
    public ResponseEntity<APIResponse> deleteTable(@PathVariable Long table_id) {
        return tableService.deleteTableByID(table_id);
    }
}
