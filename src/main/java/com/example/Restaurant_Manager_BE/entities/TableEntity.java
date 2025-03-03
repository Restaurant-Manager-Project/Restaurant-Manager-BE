package com.example.Restaurant_Manager_BE.entities;

import com.example.Restaurant_Manager_BE.enums.StatusTable;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;

import java.util.List;

@Entity
@Table(name = "tables")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Where(clause = "is_deleted = false")
public class TableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "direction")
    private String direction;

    @JoinColumn(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusTable statusTable;

    @OneToMany(mappedBy = "table")
    private List<OrderEntity> orderList;

}
