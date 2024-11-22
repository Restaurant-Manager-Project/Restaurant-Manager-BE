package com.example.Restaurant_Manager_BE.services.impl;

import com.example.Restaurant_Manager_BE.entities.RoleEntity;
import com.example.Restaurant_Manager_BE.exceptions.DataNotFoundException;
import com.example.Restaurant_Manager_BE.repositories.RoleRepository;
import com.example.Restaurant_Manager_BE.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public List<String> getPermissionKey(Long idRole) {
        RoleEntity role = roleRepository.getRoleWithPermission(idRole)
                .orElseThrow(() -> new DataNotFoundException("Role not found"));
        List<String> listKey = role.permissions.stream()
                .map(permission -> permission.getKey())
                .toList();
        return listKey;
    }
}
