package com.example.Restaurant_Manager_BE.repositories.Custom.impl;

import com.example.Restaurant_Manager_BE.dto.StatisticDTO.RevenueStatisticDTO;
import com.example.Restaurant_Manager_BE.repositories.Custom.CustomInvoiceRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class CustomInvoiceRepositoryImpl implements CustomInvoiceRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Object[]> RevenueStatisticByYear(int year) {
        String jpql ="SELECT i.timeCreate, i.total\n" +
                "    FROM InvoiceEntity i\n" +
                "    WHERE EXTRACT(YEAR FROM i.timeCreate) = :year";

        TypedQuery<Object[]> query = entityManager.createQuery(jpql, Object[].class);
        query.setParameter("year", year);
        return query.getResultList();
    }

}
