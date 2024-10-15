package com.example.Restaurant_Manager_BE.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "total")
    private Long total;
    @Column(name = "date_create")
    private Date date_create;
    @Column(name = "is_deleted")
    private Boolean is_deleted;

    @OneToMany(mappedBy = "order")
    private List<DetailsOrderEntity> detailsOrderList;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private ClientEntity client;

    public List<DetailsOrderEntity> getDetailsOrderList() {
        return detailsOrderList;
    }

    public void setDetailsOrderList(List<DetailsOrderEntity> detailsOrderList) {
        this.detailsOrderList = detailsOrderList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Date getDate_create() {
        return date_create;
    }

    public void setDate_create(Date date_create) {
        this.date_create = date_create;
    }

    public Boolean getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(Boolean is_deleted) {
        this.is_deleted = is_deleted;
    }

    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }
}
