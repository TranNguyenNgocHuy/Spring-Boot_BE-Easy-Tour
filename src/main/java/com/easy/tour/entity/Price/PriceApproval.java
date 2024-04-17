package com.easy.tour.entity.Price;

import com.easy.tour.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "price_approval")
public class PriceApproval extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Approval_Price_Id")
    private Long approvalPriceId;

    @Column(name = "Create_Date")
    private Date createDate;

    @OneToOne
    @JoinColumn(name = "Price_Id")
    private Price price;

}
