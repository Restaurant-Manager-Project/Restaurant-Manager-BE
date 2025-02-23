package com.example.Restaurant_Manager_BE.controllers;

import com.example.Restaurant_Manager_BE.services.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class RedisController {
    private final RedisService redisService;


    @PreAuthorize("hasAuthority('category.create')")
    @PostMapping("/redis/set")
    public void setRedis(@RequestBody Map<String,String> data) {
        String key = data.get("key");
        String value = data.get("value");
        System.out.println("key: " + key + " value: " + value);
        redisService.set(key, value);
    }
}
