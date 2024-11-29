package com.example.Restaurant_Manager_BE.converters;

import com.example.Restaurant_Manager_BE.dto.ClientDTO;
import com.example.Restaurant_Manager_BE.entities.ClientEntity;

public interface ClientConverter {
    ClientDTO toDTO(ClientEntity clientEntity);

}
