package com.easy.tour.entity.Price;


import com.easy.tour.Enum.ApprovalStatus;
import com.easy.tour.entity.BaseEntity;
import lombok.*;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Table(name = "price")
public class Price extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Price_Id")
    private Long priceId;

    @Column(name = "Tour_Code")
    private String tourCode;

    @Column(name = "Creator")
    private String creator;

    @Column(name = "Create_Date")
    private Date createDate;

    @Column(name = "Approved_By")
    private String approvedBy;

    @Column(name = "Approval_Date")
    private Date approvalDate;

    @Column(name = "Approval_Status")
    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus;

    @OneToOne(mappedBy = "price", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private PriceDetail priceDetail;

    @OneToOne(mappedBy = "price", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private PriceApproval priceApproval;

}
