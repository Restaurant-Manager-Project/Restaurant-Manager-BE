package com.example.Restaurant_Manager_BE.services;

import com.example.Restaurant_Manager_BE.dto.ProductDTO;
import com.example.Restaurant_Manager_BE.dto.response.ProductRespose;
import com.example.Restaurant_Manager_BE.entities.ProductEntity;
import com.example.Restaurant_Manager_BE.responses.APIResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
//    ResponseEntity<APIResponse> getAll_pagination(Integer pageNo,Integer pageSize,String sortBy);
    List<ProductRespose> getAll();
    List<ProductRespose> getByName(String name);
    boolean createProducts(ProductDTO productDTO, MultipartFile img);
    ProductRespose getById(Long id);
    boolean deleteProducts(Long id);
    boolean updateProducts(Long id , ProductDTO productDTO,MultipartFile img);
//    ResponseEntity<APIResponse> StatisticProductByCategoryAndSoldQuantity(Long id,Long topRank);
    List<ProductRespose> getProductsQuantityZero();
}
