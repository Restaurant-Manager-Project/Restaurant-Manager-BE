package com.example.Restaurant_Manager_BE.repositories.Custom;

import com.example.Restaurant_Manager_BE.entities.RoleEntity;

import java.util.Optional;

public interface CustomRole {
    public Optional<RoleEntity> getRoleWithPermission(Long idRole);
}
