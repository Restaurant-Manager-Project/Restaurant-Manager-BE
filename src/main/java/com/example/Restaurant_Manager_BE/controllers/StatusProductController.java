package com.example.Restaurant_Manager_BE.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.example.Restaurant_Manager_BE.services.StatusProductService;
import com.example.Restaurant_Manager_BE.utils.LocalizationUtils;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class StatusProductController {
    private final StatusProductService statusProductService;
    private final LocalizationUtils localizationUtils;

}
