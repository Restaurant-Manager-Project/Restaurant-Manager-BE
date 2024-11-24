package com.example.Restaurant_Manager_BE.services;

import com.example.Restaurant_Manager_BE.entities.RoleEntity;

import java.util.List;

public interface RoleService {
    public List<String> getPermissionKey(Long idRole);
}
