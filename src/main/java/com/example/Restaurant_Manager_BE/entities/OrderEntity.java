package com.example.Restaurant_Manager_BE.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Where(clause = "is_deleted = false")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "total")
    private Long total;
    @Column(name = "date_create")
    private Date dateCreate;
    @Column(name = "is_deleted")
    private Boolean isDeleted;
    @Column(name = "direction_table")
    private String directionTable;

    @OneToMany(mappedBy = "order", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH,
            CascadeType.REFRESH })
    private List<DetailsOrderEntity> detailsOrderList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_id")
    private InvoiceEntity invoice;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "table_id")
    private TableEntity table;

    @JsonIgnore
    @JoinColumn(name = "process_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private ProcessEntity process;


}
