package com.example.Restaurant_Manager_BE.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tables")
@Data
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

    @ManyToOne
    @JoinColumn(name = "status_id")
    private StatusTableEntity statusTable;

    @OneToMany(mappedBy = "table")
    private List<OrderEntity> orderList;


}
