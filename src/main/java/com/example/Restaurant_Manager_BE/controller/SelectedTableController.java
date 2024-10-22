package com.example.Restaurant_Manager_BE.controller;

import com.example.Restaurant_Manager_BE.exception.MessageResponse;
import com.example.Restaurant_Manager_BE.service.TablesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SelectedTableController {
    @Autowired
    private TablesService tablesService;
    @GetMapping("/selectedTable/{code}")
    public MessageResponse selectedTable(@PathVariable String code) {
        MessageResponse messageRespone = tablesService.findByDirection(code);
        if (messageRespone.getCode() == 200) {
            tablesService.generateDirection(Long.parseLong(messageRespone.getMessage()));
        }

        return messageRespone;
    }

    @GetMapping("/generateQRCode/{id}")
    public MessageResponse test(@PathVariable Long id) {
        MessageResponse messageRespone = tablesService.generateQRCode(id);
        return messageRespone;
    }


}
