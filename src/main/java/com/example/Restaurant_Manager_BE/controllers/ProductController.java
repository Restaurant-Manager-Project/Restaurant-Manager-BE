package com.example.Restaurant_Manager_BE.controllers;


import com.example.Restaurant_Manager_BE.constants.MessageKeys;
import com.example.Restaurant_Manager_BE.dto.ProductDTO;
import com.example.Restaurant_Manager_BE.exceptions.DataNotFoundException;
import com.example.Restaurant_Manager_BE.responses.APIResponse;
import com.example.Restaurant_Manager_BE.services.ProductService;
import com.example.Restaurant_Manager_BE.services.UploadImgFile;
import com.example.Restaurant_Manager_BE.utils.LocalizationUtils;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.apache.commons.beanutils.BeanUtils;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final LocalizationUtils localizationUtils;


    @Operation(summary = "Lấy danh sách món ăn", description = "Lấy tất cả danh sách món ăn ")
    @GetMapping("/api/products")
    public ResponseEntity<APIResponse> getAllProducts() {
        return productService.getAll();
    }

    @PreAuthorize("hasRole('product.view')")
    @Operation(summary = " Láy sản phẩm khi import")
    @GetMapping("api/import/products")
    public ResponseEntity<APIResponse> getProductsWithQuantityIsEmpty(){
        return productService.getProductsQuantityZero();
    }

    @Operation(summary = "Lấy danh sách món ăn có phân trang", description = "Lấy tất cả danh sách món ăn ")
    @GetMapping("/api/products/pagination")
    public ResponseEntity<APIResponse> getAllProducts(
            @RequestParam Integer pageNo,
            @RequestParam Integer pageSize,
            @RequestParam(value = "sortBy",required = false) String sortBy
    ) {
        return productService.getAll_pagination(pageNo, pageSize, sortBy);
    }

    @PreAuthorize("hasRole('product.view')")
    @Operation(summary = "Tìm kiếm món ăn theo tiêu chí", description = "Tìm kiếm món ăn theo tiêu chí cụ thể {name, price, ...}")
    @GetMapping("/api/products/search")
    public ResponseEntity<APIResponse> findProducts(@RequestParam Map<String, String> params) {
        return productService.getByName(params.get("name"));
    }

    //Phần Create trong CRUD
//    @PreAuthorize("hasRole('product.create')")
    @Operation(summary = "Thêm sản phẩm",description = "Thêm món ăn sau khi nhập đầy đủ thông tin")
    @PostMapping("/api/products")
    public ResponseEntity<APIResponse> CreateProduct(@RequestParam Map<String, String> map, @RequestParam(value = "img", required = false) MultipartFile img) throws IOException {
//        if( ProductDTO == null){
//            throw new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKeys.PRODUCT_CREATE_FAILED ));
//        }
        ProductDTO ProductDTO = new ProductDTO();
        try {
            BeanUtils.populate(ProductDTO, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productService.createProducts(ProductDTO, img);
    }

    @PreAuthorize("hasRole('product.delete')")
    @Operation(summary = "Xóa sản phẩm",description = "Xóa món ăn theo mã món ăn")
    @DeleteMapping("/api/products/{id}")
    public ResponseEntity<APIResponse> deleteProduct(@PathVariable("id") Long id){
        if (id == null){
            throw new DataNotFoundException(localizationUtils.getLocalizedMessage(MessageKeys.PRODUCT_DELETE_FAILED));
        }
        return productService.deleteProducts(id);
    }
    @PreAuthorize("hasRole('product.update')")
    @Operation(summary = "Sửa sản phẩm ",description = "Sửa sản phẩm theo thông tin nhập ")
    @PutMapping("/api/products/{id}")
    public ResponseEntity<APIResponse> updateProduct(
            @RequestParam Map<String, String> map, @RequestParam(value = "img", required = false) MultipartFile img
            ,@PathVariable("id") Long id){
        ProductDTO ProductDTO = new ProductDTO();
        try {
            BeanUtils.populate(ProductDTO, map);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        Long id = ProductDTO.getId();
//        productService.SkipNullFields(ProductDTO);
        return productService.updateProducts(id,ProductDTO,img);
    }
}
    