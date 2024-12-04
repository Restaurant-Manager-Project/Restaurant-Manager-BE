package com.example.Restaurant_Manager_BE.repositories.Custom.impl;

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
        String jpql = "SELECT \n" +
                "            FUNCTION('MONTH', i.timeCreate) AS month, \n" +
                "            COALESCE(SUM(i.total), 0) AS revenue\n" +
                "        FROM InvoiceEntity i\n" +
                "        WHERE FUNCTION('YEAR', i.timeCreate) = :year\n" +
                "        GROUP BY FUNCTION('MONTH', i.timeCreate)\n" +
                "        ORDER BY month";
        TypedQuery<Object[]> query = entityManager.createQuery(jpql, Object[].class);
        query.setParameter("year", year);
        return query.getResultList();
    }

}
