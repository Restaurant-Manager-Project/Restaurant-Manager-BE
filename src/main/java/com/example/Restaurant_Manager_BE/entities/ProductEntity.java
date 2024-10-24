package com.example.Restaurant_Manager_BE.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
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
    private Long quantity;
    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @OneToMany(mappedBy = "product")
    private List<DetailsImportEntity> detailsProductList;

    @OneToMany(mappedBy = "product")
    private List<DetailsOrderEntity> detailsOrderList;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;


}
