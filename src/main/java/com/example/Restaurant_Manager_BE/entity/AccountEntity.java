package com.example.Restaurant_Manager_BE.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "accounts")
public class AccountEntity {
    @Id
    private String username;
    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity role;

    @Column(name = "is_deleted")
    private Boolean is_deleted;

    @OneToOne(mappedBy = "account")
    private EmployeeEntity employee;

    public EmployeeEntity getEmployee() {
        return employee;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }

    public Boolean getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(Boolean is_deleted) {
        this.is_deleted = is_deleted;
    }
}
