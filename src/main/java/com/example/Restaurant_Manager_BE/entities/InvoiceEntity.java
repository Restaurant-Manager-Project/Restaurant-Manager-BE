package com.example.Restaurant_Manager_BE.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "invoices")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Where(clause = "is_deleted = false")
public class InvoiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "time_create")
    private Date timeCreate;

    @Column(name = "total")
    private Long total;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @OneToMany(mappedBy = "invoice", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
            CascadeType.REFRESH })
    private List<OrderEntity> orderEntityList;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private ClientEntity client;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private EmployeeEntity employee;
}
