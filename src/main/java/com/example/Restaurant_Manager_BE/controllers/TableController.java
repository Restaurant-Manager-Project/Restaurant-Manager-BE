package com.example.Restaurant_Manager_BE.controllers;

import com.example.Restaurant_Manager_BE.constants.MessageKeys;
import com.example.Restaurant_Manager_BE.dto.request.TableRequest;
import com.example.Restaurant_Manager_BE.dto.response.TableResponse;
import com.example.Restaurant_Manager_BE.enums.StatusTable;
import com.example.Restaurant_Manager_BE.responses.APIResponse;
import com.example.Restaurant_Manager_BE.services.CloudinaryService;
import com.example.Restaurant_Manager_BE.services.TableService;
import com.example.Restaurant_Manager_BE.services.ZxingService;
import com.example.Restaurant_Manager_BE.utils.LocalizationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.security.access.prepost.PreAuthorize;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TableController {
    private final TableService tableService;
    private final CloudinaryService cloudinaryService;
    private final LocalizationUtils localizationUtils;

    @Operation(summary= "Lấy tất cả bản trong db")
    @GetMapping("/api/tables")
    public ResponseEntity<APIResponse> getTables() {
        List<TableResponse> response = tableService.getALLTables();
        APIResponse apiResponse = APIResponse.builder()
                .success(true)
                .message(localizationUtils.getLocalizedMessage(MessageKeys.TABLE_GET_SUCCESS))
                .result(response)
                .build();
        return ResponseEntity.ok(apiResponse);
    }


    @PreAuthorize("hasAuthority('table_status.update')")
    @Operation(summary = "Chỉnh sửa trạ ng thái của bàn")
    @PutMapping("/api/table/{tableId}")
    public ResponseEntity<APIResponse> updateTable(@PathVariable Long tableId, @RequestBody TableRequest tableRequest) {
        
        TableResponse response = tableService.updateTable(tableId, tableRequest);
        APIResponse apiResponse = APIResponse.builder()
                .success(true)
                .message(localizationUtils.getLocalizedMessage(MessageKeys.STATUS_TABLE_UPDATED_SUCCESS))
                .result(response)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @PreAuthorize("hasAuthority('table.delete')")
    @Operation(summary = "Xóa table")
    @DeleteMapping("/api/table/{table_id}")
    public ResponseEntity<APIResponse> deleteTable(@PathVariable Long table_id) {
        TableResponse response = tableService.deleteTableByID(table_id);
        APIResponse apiResponse = APIResponse.builder()
                .success(true)
                .message(localizationUtils.getLocalizedMessage(MessageKeys.TABLE_DELETE_SUCCESS))
                .result(response)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/api/tables/{id}/qrcode")
    public ResponseEntity<APIResponse> generateQRCode(@PathVariable Long id) {
        String response = tableService.generateQRCode(id);
        APIResponse apiResponse = APIResponse.builder()
                .success(true)
                .message(localizationUtils.getLocalizedMessage(MessageKeys.TABLE_CREATE_QR_SUCCESS))
                .result(response)
                .build();
        return ResponseEntity.ok(apiResponse);
    }


        // @GetMapping("api/tables")
    // public ResponseEntity<APIResponse> selectedTable(@RequestParam String code) {
    //     if (code.isEmpty()) {
    //         throw new InvalidParamException(localizationUtils.getLocalizedMessage(MessageKeys.TABLE_NOT_FOUND));
    //     }
    //     return tableService.findByDirection(code);
    // }

    //   @GetMapping("/api/table/{direction}/details-orders")
    // public ResponseEntity<APIResponse> mergeDetailsOrder(@PathVariable String direction) {
    //     if (direction.isEmpty()) {
    //         throw new InvalidParamException(localizationUtils.getLocalizedMessage(MessageKeys.ORDER_NOT_FOUND));
    //     }
    //     return tableService.mergeAllDetailsInOrderList(direction);
    // }

}
