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

    @Override
    public List<Object[]> getStatisticProductByCategoryAndSoldQuantity(Long categoryId) {
        String jpql = "SELECT " +
                "    p.id AS product_id, " +
                "    p.name AS product_name, " +
                "    COALESCE(SUM(d.quantity), 0) AS total_quantity_sold, " +
                "    RANK() OVER (ORDER BY COALESCE(SUM(d.quantity), 0) DESC) AS rank " +
                "FROM ProductEntity p " +
                "LEFT JOIN p.detailsOrderList d " +
                "WHERE (:categoryId IS NULL OR p.category.id = :categoryId) " +
                "GROUP BY p.id, p.name " +
                "ORDER BY total_quantity_sold DESC";

        // Tạo query với kết quả là kiểu Object[]
        TypedQuery<Object[]> query = entityManager.createQuery(jpql, Object[].class);

        // Gán tham số categoryId nếu không null
        query.setParameter("categoryId", categoryId);

        return query.getResultList();
    }

}
