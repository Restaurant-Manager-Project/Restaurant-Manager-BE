package com.example.Restaurant_Manager_BE.controllers;

import com.example.Restaurant_Manager_BE.dto.RoleDTO;
import com.example.Restaurant_Manager_BE.responses.APIResponse;
import com.example.Restaurant_Manager_BE.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("api/roles/{id}")
    public ResponseEntity<APIResponse> updateRole(@PathVariable("id") Long id, @RequestBody RoleDTO roleDTO) {
        return roleService.updateRole(roleDTO,id);
    }

    @DeleteMapping("api/roles/{id}")
    public ResponseEntity<APIResponse> deleteRole(@PathVariable("id") Long id) {return roleService.deleteRole(id);}

    @GetMapping("/api/permission")
    public ResponseEntity<APIResponse> getAllPermission() {
        return roleService.getAllPermission();
    }

}
