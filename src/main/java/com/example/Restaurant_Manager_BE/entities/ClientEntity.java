package com.example.Restaurant_Manager_BE.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;


@Entity
@Table(name = "clients")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "phone")
    private String phone;
    @Column(name = "is_deleted")
    private Boolean isDeleted;
    @Column(name = "paid")
    private Long paid;

//    @OneToMany(mappedBy = "clients", fetch = FetchType.LAZY)
//    private List<InvoiceEntity> invoices;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rank_id") // Liên kết với bảng rank
    private RankEntity rank;

    // Phương thức cập nhật rank
    public void updateRank(List<RankEntity> availableRanks) {
        // filter ranks that suit with paid
        RankEntity bestRank = null;
        for (RankEntity r : availableRanks) {
            if (this.paid >= r.getEligible()) {
                if (bestRank == null || r.getEligible() > bestRank.getEligible()) {
                    bestRank = r;
                }
            }
        }
        this.rank = bestRank;
    }
}
