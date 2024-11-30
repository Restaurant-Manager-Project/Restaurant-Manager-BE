package com.example.Restaurant_Manager_BE.controllers;
import com.example.Restaurant_Manager_BE.services.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import com.example.Restaurant_Manager_BE.responses.APIResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PermissionController {
    private final PermissionService permissionService;
//    @PreAuthorize("hasRole('permission.view')")
    @GetMapping("/api/permission")
    public ResponseEntity<APIResponse> getAllPermission() {
        return permissionService.getAllPermission();
    }

}
