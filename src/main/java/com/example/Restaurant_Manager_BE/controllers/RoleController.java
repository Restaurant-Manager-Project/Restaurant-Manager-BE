package com.example.Restaurant_Manager_BE.controllers;

import com.example.Restaurant_Manager_BE.dto.RoleDTO;
import com.example.Restaurant_Manager_BE.responses.APIResponse;
import com.example.Restaurant_Manager_BE.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @GetMapping("/api/roles")
    public ResponseEntity<APIResponse> getAllRoles() {
        return roleService.getAllRoles();
    }

    @PostMapping("/api/roles")
    public ResponseEntity<APIResponse> createRole(@RequestBody RoleDTO roleDTO) {
        return roleService.createRole(roleDTO);
    }
}
