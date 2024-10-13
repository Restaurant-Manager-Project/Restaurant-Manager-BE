package com.example.Restaurant_Manager_BE.entity;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "products")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "img")
    private String img;
    @Column(name = "description")
    private String description;


    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "is_deleted")
    private Boolean is_deleted;

    @OneToMany(mappedBy = "product")
    private List<DetailsProductEntity> detailsProductList;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Boolean getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(Boolean is_deleted) {
        this.is_deleted = is_deleted;
    }

    public List<DetailsProductEntity> getDetailsProductList() {
        return detailsProductList;
    }

    public void setDetailsProductList(List<DetailsProductEntity> detailsProductList) {
        this.detailsProductList = detailsProductList;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }
}
