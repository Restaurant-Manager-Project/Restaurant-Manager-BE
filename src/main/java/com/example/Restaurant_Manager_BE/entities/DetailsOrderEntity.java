package com.example.Restaurant_Manager_BE.entities;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "details_order")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetailsOrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private Long price;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;


}
