package com.example.Restaurant_Manager_BE.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "details_product")
@Data
@AllArgsConstructor
@NoArgsConstructor
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

    @Column(name = "import_price")
    private Long importPrice;

    @Column(name = "is_deleted")
    private Boolean isDeleted;


}
