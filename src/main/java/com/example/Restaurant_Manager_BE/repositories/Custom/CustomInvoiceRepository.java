package com.example.Restaurant_Manager_BE.repositories.Custom;

import java.util.List;

public interface CustomInvoiceRepository {
    List<Object[]> RevenueStatisticByYear(int year);
}
