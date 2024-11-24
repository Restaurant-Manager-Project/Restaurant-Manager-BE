package com.example.Restaurant_Manager_BE.controllers;
import com.example.Restaurant_Manager_BE.constants.MessageKeys;
import com.example.Restaurant_Manager_BE.dto.ProductDTO;
import com.example.Restaurant_Manager_BE.exceptions.DataNotFoundException;
import com.example.Restaurant_Manager_BE.repositories.CategoryRepository;
import com.example.Restaurant_Manager_BE.responses.APIResponse;
import com.example.Restaurant_Manager_BE.services.CategoriesService;
import com.example.Restaurant_Manager_BE.utils.LocalizationUtils;
import com.example.Restaurant_Manager_BE.dto.CategoriesDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequiredArgsConstructor
public class CategoriesController {
    private final CategoriesService categoriesService;
    private final LocalizationUtils localizationUtils;

    @PreAuthorize("hasRole('category.create')")
    @Operation(summary = "Thêm loại sản phẩm",description = "Thêm loại của món ăn sau khi nhập đầy đủ thông tin")
    @PostMapping("/api/categories")
    public ResponseEntity<APIResponse> CreateCategories(@RequestBody CategoriesDTO  categoriesDTO){
        if(categoriesDTO == null){
            throw new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKeys.CATEGORY_CREATE_FAILED ));
        }
        return categoriesService.createCategories(categoriesDTO);
    }

    @PreAuthorize("hasRole('category.view')")
    @Operation(summary = "Lấy danh sách Loại ", description = "Lấy danh sách loại sản phẩm ")
    @GetMapping("/api/categories")
    public ResponseEntity<APIResponse> getALLCategories(){
        return categoriesService.getAll();
    }

    @PreAuthorize("hasRole('category.update')")
    @Operation(summary = "Xóa categories")
    @DeleteMapping("api/categories/{id}")
    public ResponseEntity<APIResponse> deleteCategory(@PathVariable Long id){
        return categoriesService.deleteCategory(id);
    }

    @PreAuthorize("hasRole('category.delete')")
    @Operation(summary="Sửa categories")
    @PutMapping("api/categories/{id}")
    public ResponseEntity<APIResponse> updateCategory(@PathVariable Long id, @RequestBody CategoriesDTO categoriesDTO){
        if(categoriesDTO == null) {
            throw new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKeys.CATEGORY_NOT_EXISTED));
        }
            return categoriesService.updateCategory(id, categoriesDTO);
    }
}
