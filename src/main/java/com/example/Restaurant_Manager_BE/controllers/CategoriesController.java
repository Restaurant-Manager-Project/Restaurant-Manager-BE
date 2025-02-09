package com.example.Restaurant_Manager_BE.controllers;
import com.example.Restaurant_Manager_BE.responses.APIResponse;
import com.example.Restaurant_Manager_BE.services.CategoriesService;
import com.example.Restaurant_Manager_BE.utils.LocalizationUtils;
import com.example.Restaurant_Manager_BE.dto.CategoriesDTO;
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
    @Operation(summary = "Thêm loại sản phẩm",description = "Thêm loại của món ăn sau khi nhập đầy đủ thông tin")
    @PostMapping("/api/categories")
    public ResponseEntity<APIResponse> CreateCategories(
            @RequestParam Map<String,String> map,
            @RequestParam(value = "img",required = false) MultipartFile imgFile
    ){
        CategoriesDTO dto = new CategoriesDTO();
        try {
            BeanUtils.populate(dto, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categoriesService.createCategories(dto, imgFile);
    }

    @Operation(summary = "Lấy danh sách Loại ", description = "Lấy danh sách loại sản phẩm ")
    @GetMapping("/api/categories")
    public ResponseEntity<APIResponse> getALLCategories(
            @RequestParam(value = "pageNo",defaultValue = "0",required = false) int pageNo,
            @RequestParam(value = "pageSize",defaultValue = "10",required = false) int pageSize
    ){
        return categoriesService.getAll(pageNo,pageSize);
    }

    @PreAuthorize("hasAuthority('category.update')")
    @Operation(summary = "Xóa categories")
    @DeleteMapping("api/categories/{id}")
    public ResponseEntity<APIResponse> deleteCategory(@PathVariable Long id){
        return categoriesService.deleteCategory(id);
    }

    @PreAuthorize("hasAuthority('category.delete')")
    @Operation(summary="Sửa categories")
    @PutMapping("api/categories/{id}")
    public ResponseEntity<APIResponse> updateCategory(
            @PathVariable Long id,
            @RequestParam Map<String,String> map,
            @RequestParam(value = "img",required = false) MultipartFile imgFile){
        CategoriesDTO dto = new CategoriesDTO();
        try {
            BeanUtils.populate(dto, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
            return categoriesService.updateCategory(id, dto,imgFile);
    }
}
