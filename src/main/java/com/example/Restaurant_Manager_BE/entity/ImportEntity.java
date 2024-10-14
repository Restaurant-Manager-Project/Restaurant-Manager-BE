package com.example.Restaurant_Manager_BE.entity;


import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "imports")
public class ImportEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private EmployeeEntity employee;

    @OneToMany(mappedBy = "importBill")
    private List<DetailsProductEntity> detailsProductList;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private SupplierEntity supplier;

    @Column(name = "date_create")
    private Date date_create;


    @Column(name = "total")
    private Long total;

    @Column(name = "is_deleted")
    private Boolean is_deleted;


}
