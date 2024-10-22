package com.example.Restaurant_Manager_BE.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "img")
    private String img;
    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Long price;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @OneToMany(mappedBy = "product")
    private List<DetailsProductEntity> detailsProductList;

    @OneToMany(mappedBy = "product")
    private List<DetailsOrderEntity> detailsOrderList;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;


}
