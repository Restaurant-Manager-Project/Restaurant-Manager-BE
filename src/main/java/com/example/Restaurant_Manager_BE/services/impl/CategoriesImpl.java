package com.example.Restaurant_Manager_BE.services.impl;
import com.example.Restaurant_Manager_BE.constants.MessageKeys;
import com.example.Restaurant_Manager_BE.dto.PaginationDTO.CategoriesDTOPagination;
import com.example.Restaurant_Manager_BE.dto.request.CategoryRequest;
import com.example.Restaurant_Manager_BE.dto.response.CategoryResponse;
import com.example.Restaurant_Manager_BE.entities.CategoryEntity;
import com.example.Restaurant_Manager_BE.exceptions.DataNotFoundException;
import com.example.Restaurant_Manager_BE.mapper.CategoryMapper;
import com.example.Restaurant_Manager_BE.services.CategoriesService;
import com.example.Restaurant_Manager_BE.repositories.CategoryRepository;
import com.example.Restaurant_Manager_BE.services.CloudinaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.Restaurant_Manager_BE.utils.LocalizationUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriesImpl implements CategoriesService {
    private final CategoryRepository categoryRepository;
    private final LocalizationUtils localizationUtils;
    private final CategoryMapper categoryMapper;
    private final CloudinaryService uploadImgFile;

    @Override
    public CategoryResponse createCategories(CategoryRequest categoryRequest, MultipartFile imgFile) {
        CategoryEntity categoryEntity = categoryMapper.toEntity(categoryRequest);
        categoryEntity.setImg(uploadImgFile.uploadImg(imgFile));
        CategoryEntity categoryCreatedEntity = categoryRepository.save(categoryEntity);
        return CategoryResponse.builder()
                .name(categoryCreatedEntity.getName())
                .img(categoryCreatedEntity.getImg())
                .build();
    }

    @Override
    public CategoryResponse deleteCategory(Long id) {
        CategoryEntity categoryEntity = categoryRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(
                        localizationUtils.getLocalizedMessage(MessageKeys.CATEGORY_DELETE_FAILED)));
        categoryEntity.setIsDeleted(true);
        CategoryEntity categoryDeletedEntity = categoryRepository.save(categoryEntity);
        return CategoryResponse.builder()
                .name(categoryDeletedEntity.getName())
                .img(categoryDeletedEntity.getImg())
                .build();
    }

    @Override
    public CategoryResponse updateCategory(Long id, CategoryRequest categoriesRequest,MultipartFile imgFile) {
        CategoryEntity categoryEntity = categoryRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(
                        localizationUtils.getLocalizedMessage(MessageKeys.CATEGORY_UPDATE_FAILED)));
        categoryEntity.setImg(uploadImgFile.uploadImg(imgFile));
        CategoryEntity categoryEntityNew = categoryMapper.update(categoryEntity, categoriesRequest);
        CategoryEntity categoryUpdatedEntity = categoryRepository.save(categoryEntityNew);
        return CategoryResponse.builder()
                .name(categoryUpdatedEntity.getName())
                .img(categoryUpdatedEntity.getImg())
                .build();
    }

    @Override
    public CategoriesDTOPagination getAll(Integer pageNo,Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<CategoryEntity> categoryEntityPage = categoryRepository.findAll(paging);
        List<CategoryEntity> categoryEntityList = categoryEntityPage.getContent();
        List<CategoryResponse> categoryPageDTO = categoryMapper.toListDto(categoryEntityList);
        return CategoriesDTOPagination
                    .builder()
                    .content(categoryPageDTO)
                    .pageNo(categoryEntityPage.getNumber())
                    .pageSize(categoryEntityPage.getSize())
                    .totalElements(categoryEntityPage.getTotalElements())
                    .totalPages(categoryEntityPage.getTotalPages())
                    .last(categoryEntityPage.isLast())
                    .build();
    }


}
