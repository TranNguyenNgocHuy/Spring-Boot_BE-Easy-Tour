package com.easy.tour.entity.Price;


import com.easy.tour.Enum.ApprovalStatus;
import com.easy.tour.entity.BaseEntity;
import com.easy.tour.entity.Tour.Tour;
import lombok.*;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@Component
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

    @Column(name = "Approval_Status")
    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus;

    @OneToOne(mappedBy = "price", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private PriceDetail priceDetail;

    @OneToOne(mappedBy = "price", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private PriceApproval priceApproval;

    @OneToOne
    @JoinColumn(name = "Tour_Code", referencedColumnName = "Tour_Code")
    private Tour tour;
}
