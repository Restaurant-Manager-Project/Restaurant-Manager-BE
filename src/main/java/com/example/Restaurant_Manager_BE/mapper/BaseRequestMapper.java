package com.example.Restaurant_Manager_BE.mapper;

import com.example.Restaurant_Manager_BE.services.CloudinaryService;
import org.mapstruct.BeanMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

/**
 * @param <D>> DTO Request type
 * @param <E> Entity type
 */
public interface BaseRequestMapper<D, E> {
    @Mapping(target = "isDeleted", expression = "java(false)")
    E toEntity(D dto);

    List<E> toListEntity(List<D> dtoList);

    // Partial Update
    @BeanMapping(nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE)
    void update(@MappingTarget E entity, D dto);


}
