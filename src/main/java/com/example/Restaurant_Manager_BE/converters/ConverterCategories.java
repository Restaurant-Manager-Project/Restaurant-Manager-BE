package com.example.Restaurant_Manager_BE.converters;

import com.example.Restaurant_Manager_BE.dto.CategoriesDTO;
import com.example.Restaurant_Manager_BE.entities.CategoryEntity;

import java.util.List;

public interface ConverterCategories {
    CategoriesDTO toDTO(CategoryEntity categoryEntity);
    CategoryEntity toEntity(CategoriesDTO categoriesDTO);
    List<CategoriesDTO> toDTOList(List<CategoryEntity> categoryEntities);
    List<CategoryEntity> toEntityList(List<CategoriesDTO> categoriesDTOList);
    void mergeNonNullFields(CategoryEntity target, CategoryEntity source);
}
