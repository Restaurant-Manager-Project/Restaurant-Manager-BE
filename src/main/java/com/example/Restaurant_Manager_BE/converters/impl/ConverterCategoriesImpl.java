package com.example.Restaurant_Manager_BE.converters.impl;

import com.example.Restaurant_Manager_BE.converters.ConverterCategories;
import com.example.Restaurant_Manager_BE.dto.CategoriesDTO;
import com.example.Restaurant_Manager_BE.entities.CategoryEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
@Component
@RequiredArgsConstructor
public class ConverterCategoriesImpl implements ConverterCategories {
    @Override
    public CategoriesDTO toDTO(CategoryEntity categoryEntity) {
        if(categoryEntity == null)return null;
        return CategoriesDTO.builder()
                .id(categoryEntity.getId().intValue())
                .img(categoryEntity.getImg())
                .name(categoryEntity.getName())
                .build();
    }

    @Override
    public CategoryEntity toEntity(CategoriesDTO categoriesDTO) {
        if(categoriesDTO == null)return null;
        return CategoryEntity.builder()
//                .id((long) categoriesDTO.getId())
                .isDeleted(false)
                .img(categoriesDTO.getImg())
                .name(categoriesDTO.getName())
                .build();
    }

    @Override
    public List<CategoriesDTO> toDTOList(List<CategoryEntity> categoryEntitiesList) {
        if(categoryEntitiesList == null || categoryEntitiesList.isEmpty())return null;
        List<CategoriesDTO> categoriesDTOList = new ArrayList<>();
        categoryEntitiesList.forEach(categoryEntity -> categoriesDTOList.add(toDTO(categoryEntity)));
        return categoriesDTOList;

    }

    @Override
    public List<CategoryEntity> toEntityList(List<CategoriesDTO> categoriesDTOList) {
        if(categoriesDTOList == null || categoriesDTOList.isEmpty())return null;
        List<CategoryEntity> categoryEntitiesList = new ArrayList<>();
        categoriesDTOList.forEach(categoryEntity -> categoryEntitiesList.add(toEntity(categoryEntity)));
        return categoryEntitiesList;
    }

    @Override
    public void mergeNonNullFields(CategoryEntity target, CategoryEntity source) {
        if (source == null) {
            return;
        }
        Field[] fields = source.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(source);
                if (value != null) {
                    field.set(target, value);
                }
            }
            catch (IllegalAccessException e) {
//                e.printStackTrace();
                return;
            }

        }
    }

}
