package com.example.Restaurant_Manager_BE.services;

import com.example.Restaurant_Manager_BE.dto.RoleDTO;
import com.example.Restaurant_Manager_BE.entities.RoleEntity;
import com.example.Restaurant_Manager_BE.responses.APIResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RoleService {
    public List<String> getPermissionKey(Long idRole);
    public ResponseEntity<APIResponse> getAllRoles();

    public ResponseEntity<APIResponse> createRole(RoleDTO roleDTO);
}
