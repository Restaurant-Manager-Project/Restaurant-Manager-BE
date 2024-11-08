package com.example.Restaurant_Manager_BE.repositories.Custom.impl;

import com.example.Restaurant_Manager_BE.dto.ProductDTO;
import com.example.Restaurant_Manager_BE.repositories.Custom.CustomProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;

public class CustomProductRepositoryImpl implements CustomProductRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<ProductDTO> getProduct_with_Price_from_Import(){
        String jpql = "SELECT new com.example.Restaurant_Manager_BE.dto.ProductDTO(p.id, p.name,p.description,p.img, di.Price,p.category.name) " +
                "FROM ProductEntity p " +
                "JOIN FETCH DetailsImportEntity di ON di.product.id = p.id "+
                "WHERE p.isDeleted = false"
                ;
        TypedQuery<ProductDTO> query=entityManager.createQuery(jpql, ProductDTO.class);
        return query.getResultList();
    }
}
