package com.example.Restaurant_Manager_BE.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "total")
    private Long total;
    @Column(name = "date_create")
    private Date dateCreate;
    @Column(name = "is_deleted")
    private Boolean isDeleted;
    @Column(name = "direction_table")
    private String directionTable;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<DetailsOrderEntity> detailsOrderList;

    @ManyToOne
    @JoinColumn(name = "table_id")
    private TableEntity table;




}
