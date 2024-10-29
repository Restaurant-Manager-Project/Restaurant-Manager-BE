package com.example.Restaurant_Manager_BE.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<DetailsImportEntity> detailsImportList;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<DetailsOrderEntity> detailsOrderList;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id")
    private StatusProductEntity statusProduct;

}
