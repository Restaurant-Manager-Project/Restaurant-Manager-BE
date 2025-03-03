package com.example.Restaurant_Manager_BE.services.impl;
import com.example.Restaurant_Manager_BE.constants.MessageKeys;
import com.example.Restaurant_Manager_BE.dto.request.CategoryRequest;
import com.example.Restaurant_Manager_BE.dto.response.CategoryResponse;
import com.example.Restaurant_Manager_BE.entities.CategoryEntity;
import com.example.Restaurant_Manager_BE.exceptions.DataNotFoundException;
import com.example.Restaurant_Manager_BE.mapper.request.CategoryRequestMapper;
import com.example.Restaurant_Manager_BE.mapper.response.CategoryResponseMapper;
import com.example.Restaurant_Manager_BE.services.CategoriesService;
import com.example.Restaurant_Manager_BE.repositories.CategoryRepository;
import com.example.Restaurant_Manager_BE.services.CloudinaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.Restaurant_Manager_BE.utils.LocalizationUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriesImpl implements CategoriesService {
    private final CategoryRepository categoryRepository;
    private final LocalizationUtils localizationUtils;
    private final CloudinaryService uploadImgFile;
    private final CategoryRequestMapper categoryRequestMapper;
    private final CategoryResponseMapper categoryResponseMapper;
    @Override
    public boolean createCategory(CategoryRequest categoryRequest) {
        CategoryEntity categoryEntity = categoryRequestMapper.toEntity(categoryRequest);
        categoryEntity.setImg(uploadImgFile.uploadImg(categoryRequest.getImg()));
        return categoryRepository.save(categoryEntity) != null ? true : false;
    }

    @Override
    public boolean deleteCategory(Long id) {
        CategoryEntity categoryEntity = categoryRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(
                        localizationUtils.getLocalizedMessage(MessageKeys.CATEGORY_DELETE_FAILED)));
        categoryEntity.setDeleted(true);
        return categoryRepository.save(categoryEntity) != null ? true : false;
    }

    @Override
    public boolean updateCategory(Long id, CategoryRequest categoryRequest) {
        CategoryEntity categoryEntity = categoryRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(
                        localizationUtils.getLocalizedMessage(MessageKeys.CATEGORY_NOT_EXISTED)));
        categoryRequestMapper.update(categoryEntity, categoryRequest);
        if (categoryRequest.getImg() != null) {
            categoryEntity.setImg(uploadImgFile.uploadImg(categoryRequest.getImg()));
        }
        return categoryRepository.save(categoryEntity) != null ? true : false;
    }

    @Override
    public List<CategoryResponse> getAll() {
        List<CategoryEntity> categoryEntities = categoryRepository.findAll();
        return categoryResponseMapper.toListDto(categoryEntities);
    }
}
