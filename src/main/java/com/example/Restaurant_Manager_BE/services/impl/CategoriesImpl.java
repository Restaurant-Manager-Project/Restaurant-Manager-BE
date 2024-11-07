package com.example.Restaurant_Manager_BE.services.impl;

import com.example.Restaurant_Manager_BE.constants.MessageKeys;
import com.example.Restaurant_Manager_BE.dto.CategoriesDTO;
import com.example.Restaurant_Manager_BE.entities.CategoryEntity;
import com.example.Restaurant_Manager_BE.exceptions.DataNotFoundException;
import com.example.Restaurant_Manager_BE.exceptions.InvalidInputException;
import com.example.Restaurant_Manager_BE.responses.APIResponse;
import com.example.Restaurant_Manager_BE.services.CategoriesService;
import com.example.Restaurant_Manager_BE.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import com.example.Restaurant_Manager_BE.utils.LocalizationUtils;
import com.example.Restaurant_Manager_BE.utils.EntityDTOconverter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoriesImpl implements CategoriesService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final LocalizationUtils localizationUtils;

    @Override
    public ResponseEntity<APIResponse> createCategories(CategoriesDTO categoriesDTO) {
        CategoryEntity categoryEntity = modelMapper.map(categoriesDTO, CategoryEntity.class);
        categoryEntity.setIsDeleted(false);
        if (categoryRepository.save(categoryEntity) == null) {
            throw new InvalidInputException(localizationUtils.getLocalizedMessage(MessageKeys.CATEGORY_CREATE_FAILED));
        }
        APIResponse APIResponse = new APIResponse();
        APIResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.CATEGORY_CREATE_SUCCESS));
        return ResponseEntity.ok(APIResponse);
    }

    @Override
    public ResponseEntity<APIResponse> deleteCategory(Long id) {
        CategoryEntity categoryEntity = categoryRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(
                        localizationUtils.getLocalizedMessage(MessageKeys.CATEGORY_DELETE_FAILED)));
        System.out.print(categoryEntity.getName());
        categoryEntity.setIsDeleted(true);
        categoryRepository.save(categoryEntity);
        APIResponse APIResponse = new APIResponse();
        APIResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.CATEGORY_DELETE_SUCCESS));
        return ResponseEntity.status(HttpStatus.OK).body(APIResponse);
    }

    @Override
    public ResponseEntity<APIResponse> updateCategory(Long id, CategoriesDTO categoriesDTO) {
        CategoryEntity categoryEntity = categoryRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(
                        localizationUtils.getLocalizedMessage(MessageKeys.CATEGORY_UPDATE_FAILED)));
        modelMapper.typeMap(CategoriesDTO.class, CategoryEntity.class)
                .addMappings(mapper -> mapper.skip(CategoryEntity::setId));
        modelMapper.map(categoriesDTO, categoryEntity);
        categoryRepository.save(categoryEntity);
        APIResponse APIResponse = new APIResponse();
        APIResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.CATEGORY_UPDATE_SUCCESS));
        return ResponseEntity.status(HttpStatus.OK).body(APIResponse);
    }

    @Override
    public ResponseEntity<APIResponse> getAll() {
        List<CategoryEntity> categoryEntityList = categoryRepository.findAll();
        List<CategoriesDTO> categoriesDTOList = categoryEntityList.stream()
                .map(entity -> EntityDTOconverter.convertToDTO(entity, CategoriesDTO.class))
                .collect(Collectors.toList());

        APIResponse APIResponse = new APIResponse();
        APIResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.CATEGORY_LIST_GET_SUCCESS));
        APIResponse.setResult(categoriesDTOList);
        return ResponseEntity.ok(APIResponse);
    }

}
