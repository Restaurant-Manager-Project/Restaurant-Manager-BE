package com.example.Restaurant_Manager_BE.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountEntity {
    @Id
    private String username;
    @Column(name = "password")
    private String password;


    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity role;

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

    @OneToOne(mappedBy = "account")
    private EmployeeEntity employee;
}
