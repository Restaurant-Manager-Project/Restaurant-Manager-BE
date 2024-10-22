package com.example.Restaurant_Manager_BE.controller;

import com.example.Restaurant_Manager_BE.exception.ErrorCode;
import com.example.Restaurant_Manager_BE.exception.MessageResponse;
import com.example.Restaurant_Manager_BE.exception.custom.BussinessException;
import com.example.Restaurant_Manager_BE.model.TablesModel;
import com.example.Restaurant_Manager_BE.service.TablesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class SelectedTableController {
    @Autowired
    private TablesService tablesService;
    @GetMapping("/table")
    public MessageResponse<TablesModel> selectedTable(@RequestParam String code) {
        if (code.isEmpty()) {
            throw new BussinessException(ErrorCode.EMPTY_INPUT_VALUE);
        }
        TablesModel tableModel = tablesService.findByDirection(code);
        MessageResponse messageRespone = new MessageResponse();
        messageRespone.setMessage("Lấy thông tin bàn thành công");
        messageRespone.setResult(tableModel);

        return messageRespone;
    }

    @GetMapping("/generateQRCode/{id}")
    public MessageResponse<String> generateQRCodeLink(@PathVariable Long id) {
        String linkQRCode = tablesService.generateQRCode(id);
        MessageResponse messageRespone = new MessageResponse();
        messageRespone.setMessage("Tạo mã QR thành công");
        messageRespone.setResult(linkQRCode);
        return messageRespone;
    }


}
