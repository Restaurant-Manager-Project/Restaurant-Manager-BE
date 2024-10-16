package com.example.Restaurant_Manager_BE.controller;

import com.example.Restaurant_Manager_BE.exception.MessageRespone;
import com.example.Restaurant_Manager_BE.service.TablesService;
import com.example.Restaurant_Manager_BE.util.QRcode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SelectedTableController {
    @Autowired
    private TablesService tablesService;
    @GetMapping("/selectedTable/{code}")
    public MessageRespone selectedTable(@PathVariable String code) {
        MessageRespone messageRespone = tablesService.findByDirection(code);
        if (messageRespone.getCode() == 200) {
            tablesService.generateDirection(Long.parseLong(messageRespone.getMessage()));
        }

        return messageRespone;
    }

    @GetMapping("/generateQRCode/{id}")
    public MessageRespone test(@PathVariable Long id) {
        MessageRespone messageRespone = tablesService.generateQRCode(id);
        return messageRespone;
    }


}
