package com.example.Restaurant_Manager_BE.entities;


import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;

import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@JsonInclude(JsonInclude.Include.NON_NULL)
@Where(clause = "is_deleted = false")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "img")
    private String img;

    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL)
    private List<ProductEntity> productList;

}
