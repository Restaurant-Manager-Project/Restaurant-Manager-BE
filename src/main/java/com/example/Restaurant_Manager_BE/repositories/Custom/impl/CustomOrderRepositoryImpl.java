package com.example.Restaurant_Manager_BE.repositories.Custom.impl;

import com.example.Restaurant_Manager_BE.entities.OrderEntity;
import com.example.Restaurant_Manager_BE.repositories.Custom.CustomOrderRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class CustomOrderRepositoryImpl implements CustomOrderRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<OrderEntity> getAllOrderWithDetailsByDirectionTable(String directionTable) {
        TypedQuery<OrderEntity> query = entityManager.createQuery("SELECT o FROM OrderEntity o JOIN FETCH o.detailsOrderList d " +
                                                                "JOIN FETCH d.product " +
                                                                "WHERE o.directionTable = :directionTable", OrderEntity.class);
        query.setParameter("directionTable", directionTable);
        return query.getResultList();
    }

    @Override
    public List<OrderEntity> getAllOrderWithTable() {
        TypedQuery<OrderEntity> query = entityManager.createQuery("SELECT o FROM OrderEntity o JOIN FETCH o.table", OrderEntity.class);
        return query.getResultList();
    }
}
