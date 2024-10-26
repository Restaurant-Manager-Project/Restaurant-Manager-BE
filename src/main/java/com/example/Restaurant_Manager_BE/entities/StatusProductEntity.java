package com.example.Restaurant_Manager_BE.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "status_product")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StatusProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @OneToMany(mappedBy = "statusProduct")
    private List<ProductEntity> productEntityList;

}
