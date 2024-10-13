package com.example.Restaurant_Manager_BE.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "employees")
public class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_Name")
    private String first_Name;

    @Column(name = "last_Name")
    private String last_Name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "gender")
    private Boolean gender;

    @Column(name = "address")
    private String address;

    @Column(name = "is_deleted")
    private Boolean is_deleted;

    @OneToOne
    @JoinColumn(name = "account_id")
    private AccountEntity account;

    @OneToMany(mappedBy = "employee")
    private List<ImportEntity> importList;


}
