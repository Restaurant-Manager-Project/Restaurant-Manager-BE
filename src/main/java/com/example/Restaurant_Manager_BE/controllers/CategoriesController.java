package com.example.Restaurant_Manager_BE.controllers;
import com.example.Restaurant_Manager_BE.constants.MessageKeys;
import com.example.Restaurant_Manager_BE.dto.PaginationDTO.CategoriesDTOPagination;
import com.example.Restaurant_Manager_BE.dto.request.CategoryRequest;
import com.example.Restaurant_Manager_BE.dto.response.CategoryResponse;
import com.example.Restaurant_Manager_BE.responses.APIResponse;
import com.example.Restaurant_Manager_BE.services.CategoriesService;
import com.example.Restaurant_Manager_BE.utils.LocalizationUtils;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class CategoriesController {
    private final CategoriesService categoriesService;
    private final LocalizationUtils localizationUtils;

    @PreAuthorize("hasAuthority('category.create')")
    @Operation(summary = "Thêm loại sản phẩm",description = "Thêm loại của món ăn")
    @PostMapping("/api/categories")
    public ResponseEntity<APIResponse> CreateCategories(@RequestParam Map<String,String> map ,@RequestParam(value = "img",required = false )MultipartFile imgFile ){
        CategoryRequest request = new CategoryRequest();
        try {BeanUtils.populate(request, map);}
        catch (Exception e) { e.printStackTrace();}
        CategoryResponse response = categoriesService.createCategories(request, imgFile);
        APIResponse apiResponse = APIResponse.builder()
                .success(true)
                .message(localizationUtils.getLocalizedMessage(MessageKeys.CATEGORY_CREATE_SUCCESS))
                .result(response)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @Operation(summary = "Lấy danh sách Loại ", description = "Lấy danh sách loại sản phẩm ")
    @GetMapping("/api/categories")
    public ResponseEntity<APIResponse> getALLCategories(
            @RequestParam(value = "pageNo",defaultValue = "0",required = false) int pageNo,
            @RequestParam(value = "pageSize",defaultValue = "10",required = false) int pageSize
    ){
        CategoriesDTOPagination response = categoriesService.getAll(pageNo,pageSize);
        APIResponse apiResponse = APIResponse.builder()
                .success(true)
                .message(localizationUtils.getLocalizedMessage(MessageKeys.CATEGORY_LIST_GET_SUCCESS))
                .result(response)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @PreAuthorize("hasAuthority('category.update')")
    @Operation(summary = "Xóa categories")
    @DeleteMapping("api/categories/{id}")
    public ResponseEntity<APIResponse> deleteCategory(@PathVariable Long id){
        CategoryResponse response = categoriesService.deleteCategory(id);
        APIResponse apiResponse = APIResponse.builder()
                .success(true)
                .message(localizationUtils.getLocalizedMessage(MessageKeys.CATEGORY_DELETE_SUCCESS))
                .result(response)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @PreAuthorize("hasAuthority('category.delete')")
    @Operation(summary="sửa categories")
    @PutMapping("api/categories/{id}")
    public ResponseEntity<APIResponse> updateCategory(
            @PathVariable Long id,
            @RequestParam Map<String,String> map,
            @RequestParam(value = "img",required = false) MultipartFile imgFile){
        CategoryRequest request = new CategoryRequest();
        try { BeanUtils.populate(request, map); }
        catch (Exception e) { e.printStackTrace(); }
        CategoryResponse response = categoriesService.updateCategory(id, request,imgFile);
        APIResponse apiResponse = APIResponse.builder()
                .success(true)
                .message(localizationUtils.getLocalizedMessage(MessageKeys.CATEGORY_UPDATE_SUCCESS))
                .result(response)
                .build();
        return ResponseEntity.ok(apiResponse);
    }
}
