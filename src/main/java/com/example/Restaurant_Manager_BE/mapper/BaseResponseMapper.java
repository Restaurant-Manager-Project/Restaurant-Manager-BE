package com.example.Restaurant_Manager_BE.mapper;

import java.util.List;

public interface BaseResponseMapper<D, E> {
    D toDto(E entity);
    List<D> toListDto(List<E> entityList);
}
