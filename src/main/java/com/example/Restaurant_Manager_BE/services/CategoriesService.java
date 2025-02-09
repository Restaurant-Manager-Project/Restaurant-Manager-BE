package com.example.Restaurant_Manager_BE.services;
import com.example.Restaurant_Manager_BE.dto.request.CategoryRequest;
import com.example.Restaurant_Manager_BE.dto.response.CategoryResponse;

import java.util.List;


public interface CategoriesService {
    boolean createCategory(CategoryRequest categoryRequest);
    List<CategoryResponse> getAll();
    boolean deleteCategory(Long id);
    boolean updateCategory(Long id, CategoryRequest categoryRequest);
}
