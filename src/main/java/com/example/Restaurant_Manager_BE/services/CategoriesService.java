package com.example.Restaurant_Manager_BE.services;
import com.example.Restaurant_Manager_BE.responses.APIResponse;
import org.springframework.http.ResponseEntity;
import com.example.Restaurant_Manager_BE.dto.CategoriesDTO;
import org.springframework.web.multipart.MultipartFile;


public interface CategoriesService {
    ResponseEntity<APIResponse> createCategories(CategoriesDTO categoryDTO, MultipartFile imgFile);
    ResponseEntity<APIResponse> getAll(Integer pageNo,Integer pageSize);
    ResponseEntity<APIResponse> deleteCategory(Long id);
    ResponseEntity<APIResponse> updateCategory(Long id, CategoriesDTO categoriesDTO,MultipartFile imgFile);
}
