package com.example.Restaurant_Manager_BE.services;

import com.example.Restaurant_Manager_BE.responses.APIResponse;
import org.springframework.http.ResponseEntity;

public interface PermissionService {
    ResponseEntity<APIResponse> getAllPermission();

}
