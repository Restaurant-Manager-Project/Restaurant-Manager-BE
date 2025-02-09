package com.example.Restaurant_Manager_BE.repositories.Custom.impl;

import com.example.Restaurant_Manager_BE.entities.ProductEntity;
import com.example.Restaurant_Manager_BE.repositories.Custom.CustomProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

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
    //important function right here
    @Override
    public List<ProductEntity> getProduct_with_Price_from_Import(){
        String jpql="SELECT  p " +
                "FROM ProductEntity p " +
                "LEFT JOIN FETCH p.detailsImportList i " +
                "LEFT JOIN FETCH p.category c "+
                "WHERE p.isDeleted = false"+
                "  AND i.importBill.id = (SELECT MAX(di2.importBill.id) " +
                "                            FROM DetailsImportEntity di2 " +
                "                            WHERE di2.product = p)";
        TypedQuery<ProductEntity> query=entityManager.createQuery(jpql, ProductEntity.class);
        return query.getResultList();
    }
    @Override
    public Page<ProductEntity> getProduct_with_Price_from_Import_Page(Pageable pageable) {
        // Query chính để lấy sản phẩm
        String jpql = "SELECT p FROM ProductEntity p WHERE p.isDeleted = false";
        TypedQuery<ProductEntity> query = entityManager.createQuery(jpql, ProductEntity.class);
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        // Query để đếm tổng số bản ghi
        String countJpql = "SELECT COUNT(p) FROM ProductEntity p WHERE p.isDeleted = false";
        TypedQuery<Long> countQuery = entityManager.createQuery(countJpql, Long.class);
        long totalElements = countQuery.getSingleResult();

        // Kết quả phân trang
        List<ProductEntity> productEntities = query.getResultList();

        // Lazy load các collection cần thiết (nếu cần)
        productEntities.forEach(product -> {
            product.getDetailsImportList().size();
            product.getCategory().getName();
        });

        return new PageImpl<>(productEntities, pageable, totalElements);
    }
    @Override
    public List<Object[]> getStatisticProductByCategoryAndSoldQuantity(Long categoryId,Long topRank) {

        String jpql = "SELECT " +
                "    p.id AS product_id, " +
                "    p.name AS product_name, " +
                "    COALESCE(SUM(d.quantity), 0) AS total_quantity_sold, " +
                "    DENSE_RANK() OVER (ORDER BY COALESCE(SUM(d.quantity), 0) DESC) AS rank " +
                "FROM ProductEntity p " +
                "LEFT JOIN p.detailsOrderList d "+
                ((categoryId != null) ? "WHERE p.category.id = :categoryId " : "") +
//                "WHERE (:categoryId IS NULL OR p.category.id = :categoryId) " +
                "GROUP BY p.id, p.name " +
                "ORDER BY total_quantity_sold DESC";

        if (topRank != null) {
            jpql += " LIMIT :topRank";
        }
        // Tạo query với kết quả là kiểu Object[]
        TypedQuery<Object[]> query = entityManager.createQuery(jpql, Object[].class);

        // Gán tham số categoryId nếu không null
        if(categoryId!=null) query.setParameter("categoryId", categoryId);
        if(topRank!=null) query.setParameter("topRank", topRank);
        return query.getResultList();
    }

}
