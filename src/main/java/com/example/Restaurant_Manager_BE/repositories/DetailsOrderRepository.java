package com.example.Restaurant_Manager_BE.repositories;

import com.example.Restaurant_Manager_BE.entities.DetailsOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DetailsOrderRepository extends JpaRepository<DetailsOrderEntity, Long> {
    @Query(value = "SELECT d.product_id, p.name, CAST(SUM(d.quantity) AS INTEGER) AS quantity, d.price , d.product_id "
            +
            "FROM products p JOIN details_order d ON p.id = d.product_id " +
            "WHERE d.order_id IN (:listOrderId) " +
            "GROUP BY d.product_id, p.name, d.price", nativeQuery = true)
    List<Map<String, Object>> mergeDetailsOrder(List<Long> listOrderId);

}
