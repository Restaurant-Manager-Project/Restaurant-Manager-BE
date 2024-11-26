package com.example.Restaurant_Manager_BE.converters.impl;

import com.example.Restaurant_Manager_BE.constants.MessageKeys;
import com.example.Restaurant_Manager_BE.converters.ConverterRole;
import com.example.Restaurant_Manager_BE.dto.RoleDTO;
import com.example.Restaurant_Manager_BE.entities.Permission;
import com.example.Restaurant_Manager_BE.entities.RoleEntity;
import com.example.Restaurant_Manager_BE.exceptions.DataNotFoundException;
import com.example.Restaurant_Manager_BE.repositories.PermissionRepository;
import com.example.Restaurant_Manager_BE.services.RoleService;
import com.example.Restaurant_Manager_BE.utils.LocalizationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ConverterRoleImpl implements ConverterRole {
    @Lazy
    private final RoleService roleService;
    private final PermissionRepository permissionRepository;
    private LocalizationUtils localizationUtils;
    @Override
    public RoleDTO toDTO(RoleEntity roleEntity) {
        RoleDTO roleDTO = RoleDTO.builder()
                .id(roleEntity.getId())
                .name(roleEntity.getName())
                .description(roleEntity.getDescription())
                .permissions(roleService.getPermissionKey(roleEntity.getId()))
                .build();
        return roleDTO;
    }

    @Override
    public RoleEntity toEntity(RoleDTO roleDTO) {
        RoleEntity roleEntity = RoleEntity.builder()
                .name(roleDTO.getName())
                .isDeleted(false)
                .description(roleDTO.getDescription())
                .build();
        List<Permission> listPermission = new ArrayList<>();
        roleDTO.getPermissions().forEach(permission -> {
            Permission permissionEntity = permissionRepository.findByKey(permission)
                    .orElseThrow(() -> new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKeys.PERMISSION_NOT_EXISTED)));
            listPermission.add(permissionEntity);
        });
        roleEntity.setPermissions(listPermission);
        return roleEntity;
    }

    @Override
    public List<RoleDTO> toDTOList(List<RoleEntity> listRole) {
        List<RoleDTO> listRoleDTO = listRole.stream()
                .map(role -> toDTO(role))
                .toList();
        return listRoleDTO;
    }

}
