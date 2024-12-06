package com.example.Restaurant_Manager_BE.services.impl;
import com.example.Restaurant_Manager_BE.constants.MessageKeys;
import com.example.Restaurant_Manager_BE.converters.ConverterCategories;
import com.example.Restaurant_Manager_BE.dto.CategoriesDTO;
import com.example.Restaurant_Manager_BE.dto.PaginationDTO.CategoriesDTOPagination;
import com.example.Restaurant_Manager_BE.entities.CategoryEntity;
import com.example.Restaurant_Manager_BE.exceptions.DataNotFoundException;
import com.example.Restaurant_Manager_BE.responses.APIResponse;
import com.example.Restaurant_Manager_BE.services.CategoriesService;
import com.example.Restaurant_Manager_BE.repositories.CategoryRepository;
import com.example.Restaurant_Manager_BE.services.UploadImgFile;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.Restaurant_Manager_BE.utils.LocalizationUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoriesImpl implements CategoriesService {
    private final CategoryRepository categoryRepository;
    private final LocalizationUtils localizationUtils;
    private final ConverterCategories converterCategories;
    private final UploadImgFile uploadImgFile;
    @Override
    public ResponseEntity<APIResponse> createCategories(CategoriesDTO categoriesDTO, MultipartFile imgFile) {
        CategoryEntity categoryEntity = converterCategories.toEntity(categoriesDTO);
        categoryEntity.setImg(uploadImgFile.uploadImg(imgFile));
        categoryRepository.save(categoryEntity);
        APIResponse APIResponse = new APIResponse();
        APIResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.CATEGORY_CREATE_SUCCESS));
        return ResponseEntity.ok(APIResponse);
    }

    @Override
    public ResponseEntity<APIResponse> deleteCategory(Long id) {
        CategoryEntity categoryEntity = categoryRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(
                        localizationUtils.getLocalizedMessage(MessageKeys.CATEGORY_DELETE_FAILED)));
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
        CategoryEntity categoryEntityUpdate = converterCategories.toEntity(categoriesDTO);
        converterCategories.mergeNonNullFields(categoryEntity,categoryEntityUpdate);
        APIResponse APIResponse = new APIResponse();
        APIResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.CATEGORY_UPDATE_SUCCESS));
        categoryRepository.save(categoryEntity);
        return ResponseEntity.status(HttpStatus.OK).body(APIResponse);
    }

    @Override
    public ResponseEntity<APIResponse> getAll(Integer pageNo,Integer pageSize) {
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<CategoryEntity> categoryEntityPage = categoryRepository.findAll(paging);
        List<CategoryEntity> categoryEntityList = categoryEntityPage.getContent();
        List<CategoriesDTO> categoryPageDTO = converterCategories.toDTOList(categoryEntityList);
//        List<CategoriesDTO> categoriesDTOList = categoryEntityList.stream()
//                .map(entity -> EntityDTOconverter.convertToDTO(entity, CategoriesDTO.class))
//                .collect(Collectors.toList());
        CategoriesDTOPagination result = new CategoriesDTOPagination();
        result.setContent(categoryPageDTO);
        result.setPageNo(categoryEntityPage.getNumber());
        result.setPageSize(categoryEntityPage.getSize());
        result.setTotalElements(categoryEntityPage.getTotalElements());
        result.setTotalPages(categoryEntityPage.getTotalPages());
        result.setLast(categoryEntityPage.isLast());
        APIResponse APIResponse = new APIResponse();
        APIResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.CATEGORY_LIST_GET_SUCCESS));
        APIResponse.setResult(result);
        return ResponseEntity.ok(APIResponse);
    }


}
