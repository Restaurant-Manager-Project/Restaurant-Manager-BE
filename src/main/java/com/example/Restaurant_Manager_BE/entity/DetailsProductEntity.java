package com.example.Restaurant_Manager_BE.entity;


import jakarta.persistence.*;
@Entity
@Table(name = "details_product")
public class DetailsProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @ManyToOne
    @JoinColumn(name = "import_id")
    private ImportEntity importBill;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "sell_price")
    private Long sell_price;

    @Column(name = "import_price")
    private Long import_price;

    @Column(name = "is_deleted")
    private Boolean is_deleted;


}
