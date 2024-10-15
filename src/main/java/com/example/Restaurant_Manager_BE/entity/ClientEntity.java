package com.example.Restaurant_Manager_BE.entity;


import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "clients")
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String first_name;
    @Column(name = "last_name")
    private String last_name;
    @Column(name = "phone")
    private String phone;
    @Column(name = "is_deleted")
    private Boolean is_deleted;

    @OneToMany(mappedBy = "client")
    private List<OrderEntity> orderList;
}
