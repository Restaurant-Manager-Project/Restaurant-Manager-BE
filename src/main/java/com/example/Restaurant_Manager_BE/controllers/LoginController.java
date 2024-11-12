package com.example.Restaurant_Manager_BE.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @GetMapping("/login")
    public String login() {
        return "Login";
    }

}
