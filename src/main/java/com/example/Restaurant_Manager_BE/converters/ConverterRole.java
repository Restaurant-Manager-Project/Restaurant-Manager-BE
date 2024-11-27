package com.example.Restaurant_Manager_BE.converters;

import com.example.Restaurant_Manager_BE.dto.PermissionDTO;
import com.example.Restaurant_Manager_BE.dto.RoleDTO;
import com.example.Restaurant_Manager_BE.entities.Permission;
import com.example.Restaurant_Manager_BE.entities.RoleEntity;

import java.util.List;

public interface ConverterRole {
    public RoleDTO toDTO(RoleEntity roleEntity);
    public RoleEntity toEntity(RoleDTO roleDTO);
    public PermissionDTO toPermissionDTO(Permission permission);
    public List<PermissionDTO> toPermissionDTOList(List<Permission> permissionList);
    public List<RoleDTO> toDTOList(List<RoleEntity> listRole);
    public void mergeNonNullFields(RoleEntity target, RoleEntity source);
}
