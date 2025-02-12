package com.example.Restaurant_Manager_BE.controllers;
import com.example.Restaurant_Manager_BE.constants.MessageKeys;
import com.example.Restaurant_Manager_BE.dto.request.CategoryRequest;
import com.example.Restaurant_Manager_BE.responses.APIResponse;
import com.example.Restaurant_Manager_BE.services.CategoriesService;
import com.example.Restaurant_Manager_BE.utils.LocalizationUtils;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequiredArgsConstructor
public class CategoriesController {
    private final CategoriesService categoriesService;
    private final LocalizationUtils localizationUtils;

    @PreAuthorize("hasAuthority('category.create')")
    @Operation(summary = "Thêm loại sản phẩm",description = "Thêm loại của món ăn sau khi nhập đầy đủ thông tin")
    @PostMapping("/api/categories")
    public ResponseEntity<APIResponse> CreateCategories(@Valid CategoryRequest categoryRequest){
        String message = categoriesService.createCategory(categoryRequest) ?
                localizationUtils.getLocalizedMessage(MessageKeys.CATEGORY_CREATE_SUCCESS) :
                localizationUtils.getLocalizedMessage(MessageKeys.CATEGORY_CREATE_FAILED);
        APIResponse res = APIResponse.builder()
                .message(message)
                .build();
        return ResponseEntity.ok(res);
    }

    @Operation(summary = "Lấy danh sách Loại ", description = "Lấy danh sách loại sản phẩm ")
    @GetMapping("/api/categories")
    public ResponseEntity<APIResponse> getAll(){
        return ResponseEntity.ok(APIResponse.builder()
                .result(categoriesService.getAll())
                .message(localizationUtils.getLocalizedMessage(MessageKeys.CATEGORY_LIST_GET_SUCCESS))
                .build());
    }

    @PreAuthorize("hasAuthority('category.update')")
    @Operation(summary = "Xóa categories")
    @DeleteMapping("api/categories/{id}")
    public ResponseEntity<APIResponse> deleteCategory(@PathVariable Long id){
        String message = categoriesService.deleteCategory(id) ?
                localizationUtils.getLocalizedMessage(MessageKeys.CATEGORY_DELETE_SUCCESS) :
                localizationUtils.getLocalizedMessage(MessageKeys.CATEGORY_DELETE_FAILED);
        APIResponse res = APIResponse.builder()
                .message(message)
                .build();
        return ResponseEntity.ok(res);

    }

    @PreAuthorize("hasAuthority('category.delete')")
    @Operation(summary="Sửa categories")
    @PutMapping("api/categories/{id}")
    public ResponseEntity<APIResponse> updateCategory(
            @PathVariable Long id,
            CategoryRequest categoryRequest){
        String message = categoriesService.updateCategory(id, categoryRequest) ?
                localizationUtils.getLocalizedMessage(MessageKeys.CATEGORY_UPDATE_SUCCESS) :
                localizationUtils.getLocalizedMessage(MessageKeys.CATEGORY_UPDATE_FAILED);
        APIResponse res = APIResponse.builder()
                .message(message)
                .build();
        return ResponseEntity.ok(res);
    }
}
