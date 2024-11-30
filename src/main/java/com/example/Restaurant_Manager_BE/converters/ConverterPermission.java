package com.example.Restaurant_Manager_BE.converters;

import com.example.Restaurant_Manager_BE.dto.PermissionDTO;
import com.example.Restaurant_Manager_BE.entities.Permission;

import java.util.List;

public interface ConverterPermission {
    PermissionDTO toPermissionDTO(Permission permission);
    List<PermissionDTO> toPermissionDTOList(List<Permission> permissionList);
}


