package com.example.Restaurant_Manager_BE.converters.impl;

import com.example.Restaurant_Manager_BE.converters.ConverterPermission;
import com.example.Restaurant_Manager_BE.dto.PermissionDTO;
import com.example.Restaurant_Manager_BE.entities.Permission;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
@RequiredArgsConstructor
@Component
public class ConverterPermissionImpl implements ConverterPermission {
    @Override
    public PermissionDTO toPermissionDTO(Permission permissionEntity) {
        if (permissionEntity == null) {return null;}
        return PermissionDTO.builder()
                .id(permissionEntity.getId())
                .name(permissionEntity.getName())
                .key(permissionEntity.getKey())
                .build();
    }

    @Override
    public List<PermissionDTO> toPermissionDTOList(List<Permission> permissionList) {
        List<PermissionDTO> permission_DTO = permissionList.stream()
                .map(per -> toPermissionDTO(per))
                .toList();
        return permission_DTO;
    }
}
