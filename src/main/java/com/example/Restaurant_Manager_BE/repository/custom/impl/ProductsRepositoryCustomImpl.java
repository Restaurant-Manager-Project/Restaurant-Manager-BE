package com.example.Restaurant_Manager_BE.repository.custom.impl;

import com.example.Restaurant_Manager_BE.entity.ProductEntity;
import com.example.Restaurant_Manager_BE.repository.custom.ProductsRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductsRepositoryCustomImpl implements ProductsRepositoryCustom {

    @PersistenceContext
    EntityManager entityManager;




    @Override
    public List<ProductEntity> getProduct() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ProductEntity> criteriaQuery = criteriaBuilder.createQuery(ProductEntity.class);
        Root<ProductEntity> product = criteriaQuery.from(ProductEntity.class);


        Predicate quantity = criteriaBuilder.greaterThan(product.get("quantity"), 0);
        Predicate is_deleted = criteriaBuilder.equal(product.get("is_deleted"), false);
        Predicate findId = criteriaBuilder.equal(product.get("id"), false);
//        criteriaQuery.select(product).where(namePredicate);

        Query query = entityManager.createQuery(criteriaQuery);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
