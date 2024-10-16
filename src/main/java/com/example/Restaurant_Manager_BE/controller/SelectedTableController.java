package com.example.Restaurant_Manager_BE.controller;

import com.example.Restaurant_Manager_BE.exception.MessageRespone;
import com.example.Restaurant_Manager_BE.service.TablesService;
import com.example.Restaurant_Manager_BE.util.QRcode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SelectedTableController {
    @Autowired
    private TablesService tablesService;
    @GetMapping("/selectedTable/{code}")
    public MessageRespone selectedTable(@PathVariable String code) {
        String password = QRcode.decoding(code);
        return tablesService.findTableByPassword(password);
    }

    @PostMapping("/selectedTable/")
    public void test() {
        String password = QRcode.generatePassword(10);
        String encode = QRcode.encoding(password);

        String decode = QRcode.decoding(encode);
        System.out.println("zzzzz");
    }
}
