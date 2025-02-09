package com.example.Restaurant_Manager_BE.services;

import com.example.Restaurant_Manager_BE.dto.request.ProductRequest;
import com.example.Restaurant_Manager_BE.dto.response.ProductRespose;

import java.util.List;

public interface ProductService {
//    ResponseEntity<APIResponse> getAll_pagination(Integer pageNo,Integer pageSize,String sortBy);
    List<ProductRespose> getAll();
    List<ProductRespose> getByName(String name);
    boolean createProducts(ProductRequest productRequest);
    ProductRespose getById(Long id);
    boolean deleteProducts(Long id);
    boolean updateProducts(Long id , ProductRequest productRequest);
//    ResponseEntity<APIResponse> StatisticProductByCategoryAndSoldQuantity(Long id,Long topRank);
    List<ProductRespose> getProductsQuantityZero();
}
