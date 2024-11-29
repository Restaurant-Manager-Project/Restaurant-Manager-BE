package com.example.Restaurant_Manager_BE.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "ranks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RankEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "discount")
    private int discountPercent;

    @Column(name = "eligible")
    private Long eligible; // Ngưỡng đạt (vd: 100k)

}
