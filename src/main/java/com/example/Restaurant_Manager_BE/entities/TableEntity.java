package com.example.Restaurant_Manager_BE.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "tables")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "direction")
    private String direction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id")
    private StatusTableEntity statusTable;

    @OneToMany(mappedBy = "table")
    private List<OrderEntity> orderList;

}
