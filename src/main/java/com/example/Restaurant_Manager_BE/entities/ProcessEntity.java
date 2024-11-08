package com.example.Restaurant_Manager_BE.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "processes")
public class ProcessEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private Boolean isDeleted;

    @JsonIgnore
    @OneToMany(mappedBy = "process")
    private List<OrderEntity> orderList;



}
