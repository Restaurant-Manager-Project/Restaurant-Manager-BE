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
        String jpql = " SELECT \n" +
                "    months.month AS thang,\n" +
                "    COALESCE(SUM(DISTINCT hoadon.tongtien), 0) AS doanhthu,\n" +
                "\n" +
                "FROM (\n" +
                "    SELECT 1 AS month\n" +
                "    UNION ALL SELECT 2\n" +
                "    UNION ALL SELECT 3\n" +
                "    UNION ALL SELECT 4\n" +
                "    UNION ALL SELECT 5\n" +
                "    UNION ALL SELECT 6\n" +
                "    UNION ALL SELECT 7\n" +
                "    UNION ALL SELECT 8\n" +
                "    UNION ALL SELECT 9\n" +
                "    UNION ALL SELECT 10\n" +
                "    UNION ALL SELECT 11\n" +
                "    UNION ALL SELECT 12\n" +
                ") AS months\n" +
                "LEFT JOIN hoadon ON MONTH(hoadon.NgayLap) = months.month and year(hoadon.NgayLap)= $year\n" +
                "\n" +
                "GROUP BY months.month\n" +
                "ORDER BY months.month;";

        TypedQuery<Object[]> query = entityManager.createQuery(jpql, Object[].class);
        query.setParameter("year", year);
        return query.getResultList();
    }

}
