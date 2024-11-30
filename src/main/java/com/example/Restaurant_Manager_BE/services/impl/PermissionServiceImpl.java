package com.example.Restaurant_Manager_BE.services.impl;

import com.example.Restaurant_Manager_BE.constants.MessageKeys;
import com.example.Restaurant_Manager_BE.converters.ConverterPermission;
import com.example.Restaurant_Manager_BE.dto.PermissionDTO;
import com.example.Restaurant_Manager_BE.entities.Permission;
import com.example.Restaurant_Manager_BE.repositories.PermissionRepository;
import com.example.Restaurant_Manager_BE.responses.APIResponse;
import com.example.Restaurant_Manager_BE.services.PermissionService;
import com.example.Restaurant_Manager_BE.utils.LocalizationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {
    private final PermissionRepository permissionRepository;
    private final LocalizationUtils localizationUtils;
    private final ConverterPermission converterPermission;
    @Override
    public ResponseEntity<APIResponse> getAllPermission() {
        List<Permission> per = permissionRepository.findAll();
        List<PermissionDTO> perDTO = converterPermission.toPermissionDTOList(per);

        APIResponse apiResponse = APIResponse.builder()
                .result(perDTO)
                .message(localizationUtils.getLocalizedMessage(MessageKeys.PERMISSION_LIST_GET_SUCCESS))
                .success(true)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}
