package com.example.Restaurant_Manager_BE.converters.impl;

import com.example.Restaurant_Manager_BE.converters.ClientConverter;
import com.example.Restaurant_Manager_BE.dto.ClientDTO;
import com.example.Restaurant_Manager_BE.entities.ClientEntity;
import org.springframework.stereotype.Component;

@Component
public class ClientConverterImpl implements ClientConverter {
    @Override
    public ClientDTO toDTO(ClientEntity clientEntity){
        if(clientEntity == null)return null;
        return ClientDTO.builder()
                .id(clientEntity.getId())
                .firstName(clientEntity.getFirstName())
                .lastName(clientEntity.getLastName())
                .rank_id(clientEntity.getRank().getId())
                .paid(clientEntity.getPaid())
                .phone(clientEntity.getPhone())
                .build();
    }
}
