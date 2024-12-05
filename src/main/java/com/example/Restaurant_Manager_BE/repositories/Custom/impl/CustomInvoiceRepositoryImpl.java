package com.example.Restaurant_Manager_BE.repositories.Custom.impl;

import com.example.Restaurant_Manager_BE.dto.StatisticDTO.RevenueStatisticDTO;
import com.example.Restaurant_Manager_BE.repositories.Custom.CustomInvoiceRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.*;
import java.util.stream.Collectors;  // Import Collectors
import java.time.LocalDate;
import java.time.ZoneId;

public class CustomInvoiceRepositoryImpl implements CustomInvoiceRepository {
    @PersistenceContext
    private EntityManager entityManager;
    private List<Object[]> convertMapToList(Map<Integer, Long> monthRevenueMap) {
        List<Object[]> resultList = new ArrayList<>();
        for (Map.Entry<Integer, Long> entry : monthRevenueMap.entrySet()) {
            resultList.add(new Object[]{entry.getKey(), entry.getValue()});
        }
        return resultList;
    }
    @Override
    public List<Object[]> RevenueStatisticByYear(int year) {
        String jpql ="SELECT i.timeCreate, i.total\n" +
                "    FROM InvoiceEntity i\n" +
                "    WHERE EXTRACT(YEAR FROM i.timeCreate) = :year";

        TypedQuery<Object[]> query = entityManager.createQuery(jpql, Object[].class);
        query.setParameter("year", year);
        List<Object[]> results = query.getResultList();
        // Xử lý ép kiểu cho months và revenue bằng hàm phụ
        Map<Integer, Long> monthRevenueMap = processResult(results);

        return convertMapToList(monthRevenueMap);  // Trả về bản đồ doanh thu theo tháng

    }
    // Hàm xử lý ép kiểu cho months và revenue
    private Map<Integer, Long> processResult(List<Object[]> results) {
        // Khởi tạo Map với tháng từ 1 đến 12, mỗi tháng đều có doanh thu ban đầu là 0
        Map<Integer, Long> monthRevenueMap = new HashMap<>();
        for (int i = 1; i <= 12; i++) {
            monthRevenueMap.put(i, 0L);  // Khởi tạo tháng với doanh thu = 0
        }

        for (Object[] result : results) {
            // Lấy tháng từ timeCreate (result[0])
            int month = 0;
            if (result[0] instanceof Date) {
                LocalDate localDate = LocalDate.ofInstant(((Date) result[0]).toInstant(), ZoneId.systemDefault());
                month = localDate.getMonthValue(); // Lấy tháng (1-12)
            }

            // Lấy revenue từ total (result[1])
            Long revenue = (result[1] instanceof Number) ? ((Number) result[1]).longValue() : 0L;

            // Cộng dồn doanh thu vào tháng tương ứng
            monthRevenueMap.put(month, monthRevenueMap.getOrDefault(month, 0L) + revenue);
        }

        return monthRevenueMap;
    }

}
