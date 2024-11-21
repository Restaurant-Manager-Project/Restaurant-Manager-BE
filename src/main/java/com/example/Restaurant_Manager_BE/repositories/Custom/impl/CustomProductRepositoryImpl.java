package com.example.Restaurant_Manager_BE.repositories.Custom.impl;

import com.example.Restaurant_Manager_BE.dto.ProductDTO;
import com.example.Restaurant_Manager_BE.entities.ProductEntity;
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
    public List<ProductDTO> getProduct_with_Price_from_Import_price_notNUll(){
        String jpql = "SELECT new com.example.Restaurant_Manager_BE.dto.ProductDTO(p.id, p.name,p.description,p.img, di.Price,p.category.id,p.quantity) " +
                "FROM ProductEntity p " +
                "LEFT JOIN DetailsImportEntity di ON di.product.id = p.id "+
                "WHERE p.isDeleted = false ";
                ;
        TypedQuery<ProductDTO> query=entityManager.createQuery(jpql, ProductDTO.class);
        return query.getResultList();
    }

    @Override
    public List<ProductEntity> getProduct_with_Price_from_Import(){
        String jpql="SELECT  p " +
                "FROM ProductEntity p " +
                "LEFT JOIN FETCH p.detailsImportList i " +
                "WHERE p.isDeleted = false";
        ;
        TypedQuery<ProductEntity> query=entityManager.createQuery(jpql, ProductEntity.class);
        return query.getResultList();
    }
}
