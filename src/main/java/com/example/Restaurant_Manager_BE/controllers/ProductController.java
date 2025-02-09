package com.example.Restaurant_Manager_BE.controllers;


import com.example.Restaurant_Manager_BE.constants.MessageKeys;
import com.example.Restaurant_Manager_BE.dto.request.ProductRequest;
import com.example.Restaurant_Manager_BE.exceptions.DataNotFoundException;
import com.example.Restaurant_Manager_BE.responses.APIResponse;
import com.example.Restaurant_Manager_BE.services.ProductService;
import com.example.Restaurant_Manager_BE.utils.LocalizationUtils;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final LocalizationUtils localizationUtils;


    @Operation(summary = "Lấy danh sách món ăn", description = "Lấy tất cả danh sách món ăn ")
    @GetMapping("/api/products")
    public ResponseEntity<APIResponse> getAllProducts() {
        APIResponse apiResponse = APIResponse.builder()
                .message(localizationUtils.getLocalizedMessage(MessageKeys.PRODUCT_LIST_GET_SUCCESS))
                .result(productService.getAll())
                .build();
        return ResponseEntity.ok(apiResponse);
    }

//    @PreAuthorize("hasAuthority('product.view')")
//    @Operation(summary = " Láy sản phẩm khi import")
//    @GetMapping("api/import/products")
//    public ResponseEntity<APIResponse> getProductsWithQuantityIsEmpty(){
//        return productService.getProductsQuantityZero();
//    }
//
//    @Operation(summary = "Lấy danh sách món ăn có phân trang", description = "Lấy tất cả danh sách món ăn ")
//    @GetMapping("/api/products/pagination")
//    public ResponseEntity<APIResponse> getAllProducts(
//            @RequestParam Integer pageNo,
//            @RequestParam Integer pageSize,
//            @RequestParam(value = "sortBy",required = false) String sortBy
//    ) {
//        return productService.getAll_pagination(pageNo, pageSize, sortBy);
//    }
//
//    @PreAuthorize("hasAuthority('product.view')")
//    @Operation(summary = "Tìm kiếm món ăn theo tiêu chí", description = "Tìm kiếm món ăn theo tiêu chí cụ thể {name, price, ...}")
//    @GetMapping("/api/products/search")
//    public ResponseEntity<APIResponse> findProducts(@RequestParam Map<String, String> params) {
//        return productService.getByName(params.get("name"));
//    }
//
    //Phần Create trong CRUD
    @PreAuthorize("hasAuthority('product.create')")
    @Operation(summary = "Thêm sản phẩm",description = "Thêm món ăn sau khi nhập đầy đủ thông tin")
    @PostMapping(value = "/api/products", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<APIResponse> CreateProduct(ProductRequest p)  {
        String message = productService.createProducts(p) ?
                localizationUtils.getLocalizedMessage(MessageKeys.PRODUCT_CREATE_SUCCESS) :
                localizationUtils.getLocalizedMessage(MessageKeys.PRODUCT_CREATE_FAILED);
        APIResponse apiResponse = APIResponse.builder()
                .message(message)
                .build();
        return ResponseEntity.ok(apiResponse);

    }

    @PreAuthorize("hasAuthority('product.delete')")
    @Operation(summary = "Xóa sản phẩm",description = "Xóa món ăn theo mã món ăn")
    @DeleteMapping("/api/products/{id}")
    public ResponseEntity<APIResponse> deleteProduct(@PathVariable("id") Long id){
        if (id == null){
            throw new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKeys.PRODUCT_DELETE_FAILED));
        }
        String message = productService.deleteProducts(id) ?
                localizationUtils.getLocalizedMessage(MessageKeys.PRODUCT_DELETE_SUCCESS) :
                localizationUtils.getLocalizedMessage(MessageKeys.PRODUCT_DELETE_FAILED);
        APIResponse apiResponse = APIResponse.builder()
                .message(message)
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @PreAuthorize("hasAuthority('product.update')")
    @Operation(summary = "Sửa sản phẩm ",description = "Sửa sản phẩm theo thông tin nhập ")
    @PutMapping("/api/products/{id}")
    public ResponseEntity<APIResponse> updateProduct(
            @PathVariable("id") Long id,
            ProductRequest productRequest){
        String message = productService.updateProducts(id,productRequest) ?
                localizationUtils.getLocalizedMessage(MessageKeys.PRODUCT_UPDATE_SUCCESS) :
                localizationUtils.getLocalizedMessage(MessageKeys.PRODUCT_UPDATE_FAILED);
        APIResponse apiResponse = APIResponse.builder()
                .message(message)
                .build();
        return ResponseEntity.ok(apiResponse);
    }
}
    