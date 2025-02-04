package com.example.Restaurant_Manager_BE.services;
import com.example.Restaurant_Manager_BE.dto.PaginationDTO.CategoriesDTOPagination;
import com.example.Restaurant_Manager_BE.dto.request.CategoryRequest;
import com.example.Restaurant_Manager_BE.dto.response.CategoryResponse;

import org.springframework.web.multipart.MultipartFile;


public interface CategoriesService {
    CategoryResponse createCategories(CategoryRequest categoryRequest, MultipartFile imgFile);
    CategoriesDTOPagination getAll(Integer pageNo,Integer pageSize);
    CategoryResponse deleteCategory(Long id);
    CategoryResponse updateCategory(Long id, CategoryRequest categoriesRequest,MultipartFile imgFile);
}
