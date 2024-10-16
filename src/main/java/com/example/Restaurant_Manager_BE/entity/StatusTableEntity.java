package com.example.Restaurant_Manager_BE.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "status_table")
public class StatusTableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "is_deleted")
    private Boolean is_deleted;

    @OneToMany(mappedBy = "statusTable")
    private List<TableEntity> tableList;

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

    public List<TableEntity> getTableList() {
        return tableList;
    }

    public void setTableList(List<TableEntity> tableList) {
        this.tableList = tableList;
    }
}
