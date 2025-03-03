package com.example.Restaurant_Manager_BE.entities;

import com.example.Restaurant_Manager_BE.enums.StatusProduct;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;

import java.util.List;


@Entity
@Table(name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Where(clause = "is_deleted = false")
//@JsonInclude(JsonInclude.Include.NON_NULL)
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

    @OneToMany(mappedBy = "product",fetch = FetchType.LAZY)
    private List<DetailsImportEntity> detailsImportList;

    @OneToMany(mappedBy = "product")
    private List<DetailsOrderEntity> detailsOrderList;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @JoinColumn(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusProduct statusProduct;

}
