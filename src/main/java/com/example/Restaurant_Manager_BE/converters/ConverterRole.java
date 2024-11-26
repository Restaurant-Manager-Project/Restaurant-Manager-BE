package com.example.Restaurant_Manager_BE.converters;

import com.example.Restaurant_Manager_BE.dto.RoleDTO;
import com.example.Restaurant_Manager_BE.entities.RoleEntity;

import java.util.List;

public interface ConverterRole {
    public RoleDTO toDTO(RoleEntity roleEntity);
    public RoleEntity toEntity(RoleDTO roleDTO);

    public List<RoleDTO> toDTOList(List<RoleEntity> listRole);
}
