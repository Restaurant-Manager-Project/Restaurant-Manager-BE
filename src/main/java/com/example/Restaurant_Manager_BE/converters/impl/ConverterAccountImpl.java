package com.example.Restaurant_Manager_BE.converters.impl;

import com.example.Restaurant_Manager_BE.constants.MessageKeys;
import com.example.Restaurant_Manager_BE.converters.ConverterAccount;
import com.example.Restaurant_Manager_BE.dto.AccountDTO;
import com.example.Restaurant_Manager_BE.entities.AccountEntity;
import com.example.Restaurant_Manager_BE.entities.EmployeeEntity;
import com.example.Restaurant_Manager_BE.entities.RoleEntity;
import com.example.Restaurant_Manager_BE.exceptions.DataNotFoundException;
import com.example.Restaurant_Manager_BE.repositories.AccountRepository;
import com.example.Restaurant_Manager_BE.repositories.RoleRepository;
import com.example.Restaurant_Manager_BE.utils.LocalizationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConverterAccountImpl implements ConverterAccount {
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final LocalizationUtils localizationUtils;
    @Override
    public AccountEntity toEntity(AccountDTO dto, EmployeeEntity employee) {
        if(dto == null || employee == null) {return null;}
        AccountEntity.AccountEntityBuilder entity= AccountEntity.builder()
                .employee(employee)
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()));
        if(dto.getRole_id() != null) {
            RoleEntity role = roleRepository.findById(dto.getRole_id())
                    .orElseThrow(() -> new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKeys.ROLE_NOT_EXISTED)));
            entity.role(role);
        }
        return entity.build();
    }
    @Override
    public AccountDTO toDto(AccountEntity entity) {
        if(entity == null) {return null;}
        Long roleId = (entity.getRole() != null) ? entity.getRole().getId() : null;

        return AccountDTO.builder()
                .username(entity.getUsername())
                .password(entity.getPassword())
                .role_id(roleId)
                .build();
    }
}
