package com.example.Restaurant_Manager_BE.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "imports")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Where(clause = "is_deleted = false")
public class ImportEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private EmployeeEntity employee;

    @JsonIgnore
    @OneToMany(mappedBy = "importBill", cascade = CascadeType.ALL)
    private List<DetailsImportEntity> detailsProductList;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private SupplierEntity supplier;

    @Column(name = "date_create")
    private Date dateCreate;

    @Column(name = "total")
    private Long total;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

}
