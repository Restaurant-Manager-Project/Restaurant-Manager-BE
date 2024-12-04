package com.example.Restaurant_Manager_BE.converters;


import com.example.Restaurant_Manager_BE.dto.AccountDTO;
import com.example.Restaurant_Manager_BE.entities.AccountEntity;
import com.example.Restaurant_Manager_BE.entities.EmployeeEntity;

public interface ConverterAccount {
    AccountEntity toEntity(AccountDTO dto, EmployeeEntity employee);
    AccountDTO toDto(AccountEntity entity);
}
