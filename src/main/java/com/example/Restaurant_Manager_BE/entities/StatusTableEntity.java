package com.example.Restaurant_Manager_BE.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "status_table")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatusTableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @OneToMany(mappedBy = "statusTable")
    private List<TableEntity> tableList;


}
