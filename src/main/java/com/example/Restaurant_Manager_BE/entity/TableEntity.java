package com.example.Restaurant_Manager_BE.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tables")
public class TableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "is_deleted")
    private Boolean is_deleted;

    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private StatusTableEntity statusTable;

    @OneToMany(mappedBy = "table")
    private List<OrderEntity> orderList;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(Boolean is_deleted) {
        this.is_deleted = is_deleted;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public StatusTableEntity getStatusTable() {
        return statusTable;
    }

    public void setStatusTable(StatusTableEntity statusTable) {
        this.statusTable = statusTable;
    }

    public List<OrderEntity> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderEntity> orderList) {
        this.orderList = orderList;
    }


}
