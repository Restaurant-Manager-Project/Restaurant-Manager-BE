package com.example.Restaurant_Manager_BE.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.List;

/**
 * @param <DRQ> DTO Request type
 * @param <QRP> DTO Response type
 * @param <E> Entity type
 */
public interface BaseMapper<DRQ, QRP, E> {

    E toEntity(DRQ dto);

    QRP toDto(E entity);

    List<E> toListEntity(List<DRQ> dtoList);

    List<QRP> toListDto(List<E> entityList);


    // Partial Update
    @BeanMapping(nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE)
    E update(@MappingTarget E entity, DRQ dto);


}
