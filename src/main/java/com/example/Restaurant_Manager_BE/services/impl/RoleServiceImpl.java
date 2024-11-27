package com.example.Restaurant_Manager_BE.services.impl;

import com.example.Restaurant_Manager_BE.constants.MessageKeys;
import com.example.Restaurant_Manager_BE.converters.ConverterRole;
import com.example.Restaurant_Manager_BE.dto.PermissionDTO;
import com.example.Restaurant_Manager_BE.dto.RoleDTO;
import com.example.Restaurant_Manager_BE.entities.Permission;
import com.example.Restaurant_Manager_BE.entities.RoleEntity;
import com.example.Restaurant_Manager_BE.exceptions.DataNotFoundException;
import com.example.Restaurant_Manager_BE.repositories.PermissionRepository;
import com.example.Restaurant_Manager_BE.repositories.RoleRepository;
import com.example.Restaurant_Manager_BE.responses.APIResponse;
import com.example.Restaurant_Manager_BE.services.RoleService;
import com.example.Restaurant_Manager_BE.utils.LocalizationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final LocalizationUtils localizationUtils;
    private final ConverterRole converterRole;
    private final PermissionRepository permissionRepository;
    @Override
    public List<String> getPermissionKey(Long idRole) {
        RoleEntity role = roleRepository.getRoleWithPermission(idRole)
                .orElseThrow(() -> new DataNotFoundException("Role not found"));
        List<String> listKey = role.permissions.stream()
                .map(permission -> permission.getKey())
                .toList();
        return listKey;
    }

    @Override
    public ResponseEntity<APIResponse> getAllRoles() {
        List<RoleEntity> listRole = roleRepository.findAll();
        List<RoleDTO> listRoleDTO = converterRole.toDTOList(listRole);
        APIResponse apiResponse = APIResponse.builder()
                .result(listRoleDTO)
                .message(localizationUtils.getLocalizedMessage(MessageKeys.ROLE_LIST_GET_SUCCESS))
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @Override
    public ResponseEntity<APIResponse> createRole(RoleDTO roleDTO) {
        RoleEntity role = converterRole.toEntity(roleDTO);
        roleRepository.save(role);
        APIResponse apiResponse = APIResponse.builder()
                .result(converterRole.toDTO(role))
                .message(localizationUtils.getLocalizedMessage(MessageKeys.ROLE_CREATE_SUCCESS))
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @Override
    public ResponseEntity<APIResponse> getAllPermission() {
        List<Permission> per = permissionRepository.findAll();
        List<PermissionDTO> perDTO = converterRole.toPermissionDTOList(per);

        APIResponse apiResponse = APIResponse.builder()
                .result(perDTO)
                .message(localizationUtils.getLocalizedMessage(MessageKeys.PERMISSION_LIST_GET_SUCCESS))
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @Override
    public ResponseEntity<APIResponse> updateRole(RoleDTO roleDTO, Long id) {
        RoleEntity role = roleRepository.findById(id)
                .orElseThrow(()->new DataNotFoundException(MessageKeys.ROLE_NOT_EXISTED));
        RoleEntity roleUpdate = converterRole.toEntity(roleDTO);
        converterRole.mergeNonNullFields(role,roleUpdate);
        roleRepository.save(role);
        APIResponse apiResponse = APIResponse.builder()
                .message(localizationUtils.getLocalizedMessage(MessageKeys.ROlE_UPDATED_SUCCESS))
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @Override
    public ResponseEntity<APIResponse> deleteRole(Long id) {
        RoleEntity role = roleRepository.findById(id)
                .orElseThrow(()->new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKeys.ROLE_NOT_EXISTED)));
        role.setIsDeleted(true);
        roleRepository.save(role);
        APIResponse apiResponse = APIResponse.builder()
                .message(localizationUtils.getLocalizedMessage(MessageKeys.ROlE_UPDATED_SUCCESS))
                .build();
        return ResponseEntity.ok(apiResponse);
    }
}
