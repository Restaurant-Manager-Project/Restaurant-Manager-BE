package com.example.Restaurant_Manager_BE.entities;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "details_import")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetailsImportEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "import_id")
    private ImportEntity importBill;

    @Column(name = "quantity")
    private Long quantity;

    @Column(name = "import_price")
    private Long importPrice;

    @Column(name = "sell_price")
    private Long Price;

    @Column(name = "is_deleted")
    private Boolean isDeleted;


}
