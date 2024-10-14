package com.example.Restaurant_Manager_BE.entity;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "suppliers")
public class SupplierEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "phone")
    private String phone;
    @Column(name = "is_deleted")
    private Boolean is_deleted;

    @OneToMany(mappedBy = "supplier")
    private List<ImportEntity> importList;


}
