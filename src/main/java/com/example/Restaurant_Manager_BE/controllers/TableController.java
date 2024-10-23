package com.example.Restaurant_Manager_BE.controllers;

import com.example.Restaurant_Manager_BE.constants.MessageKeys;
import com.example.Restaurant_Manager_BE.exceptions.BussinessException;
import com.example.Restaurant_Manager_BE.exceptions.ErrorCode;
import com.example.Restaurant_Manager_BE.responses.APIResponse;
import com.example.Restaurant_Manager_BE.dto.TableDTO;
import com.example.Restaurant_Manager_BE.services.TableService;
import com.example.Restaurant_Manager_BE.utils.LocalizationUtils;
import lombok.RequiredArgsConstructor;
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
    public APIResponse selectedTable(@RequestParam String code) {
        if (code.isEmpty()) {
            throw new BussinessException(ErrorCode.EMPTY_INPUT_VALUE);
        }
        TableDTO tableModel = tableService.findByDirection(code);
        APIResponse messageRespone = new APIResponse();
        messageRespone.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.TABLE_GET_SUCCESS));
        messageRespone.setResult(tableModel);
        return messageRespone;
    }

    @GetMapping("tables/generateQRCode/{id}")
    public APIResponse generateQRCodeLink(@PathVariable Long id) {
        String linkQRCode = tableService.generateQRCode(id);
        APIResponse messageRespone = new APIResponse();
        messageRespone.setMessage("Tạo mã QR thành công");
        messageRespone.setResult(linkQRCode);
        return messageRespone;
    }
}
