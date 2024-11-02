package com.example.Restaurant_Manager_BE.utils;

import org.modelmapper.ModelMapper;

public class EntityDTOconverter {

    private static final ModelMapper modelMapper = new ModelMapper();

    // Chuyển đổi từ Entity sang DTO
    public static <D, E> D convertToDTO(E entity, Class<D> dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }

    // Chuyển đổi từ DTO sang Entity
    public static <E, D> E convertToEntity(D dto, Class<E> entityClass) {
        return modelMapper.map(dto, entityClass);
    }
}

