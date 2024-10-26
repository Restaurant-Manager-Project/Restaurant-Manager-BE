package com.example.Restaurant_Manager_BE.services.impl;
import com.example.Restaurant_Manager_BE.constants.MessageKeys;
import com.example.Restaurant_Manager_BE.dto.CategoriesDTO;
import com.example.Restaurant_Manager_BE.entities.CategoryEntity;
import com.example.Restaurant_Manager_BE.exceptions.InvalidInputException;
import com.example.Restaurant_Manager_BE.responses.APIResponse;
import com.example.Restaurant_Manager_BE.services.CategoriesService;
import com.example.Restaurant_Manager_BE.entities.CategoryEntity;
import com.example.Restaurant_Manager_BE.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import com.example.Restaurant_Manager_BE.utils.LocalizationUtils;
@Service
@RequiredArgsConstructor
public class CategoriesImpl implements CategoriesService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;
    private final LocalizationUtils localizationUtils;

        public ResponseEntity<APIResponse> createCategories(CategoriesDTO categoriesDTO) {
            CategoryEntity categoryEntity = modelMapper.map(categoriesDTO, CategoryEntity.class);
            categoryEntity.setIsDeleted(false);
            if (categoryRepository.save(categoryEntity) == null) {
                throw new InvalidInputException(localizationUtils.getLocalizedMessage(MessageKeys.CLIENT_CREATE_FALIED));
            }
            APIResponse APIResponse = new APIResponse();
            APIResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.CLIENT_CREATE_SUCCESS));
            return ResponseEntity.ok(APIResponse);
        }

}
