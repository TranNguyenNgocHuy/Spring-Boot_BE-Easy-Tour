package com.easy.tour.entity.Refund;

import com.easy.tour.entity.BaseEntity;
import groovy.transform.EqualsAndHashCode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "refund_request")
@EqualsAndHashCode
public class RefundRequest extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RefundRequest_Id")
    private Long refundRequestId;

    @Column(name = "tour_code")
    private String tourCode;

    @Column(name = "customer")
    private String customer;

    @Column(name = "phone_number")
    private Integer phoneNumber;

    @Column(name = "refund_amount")
    private Integer refundAmount;

    @Column(name = "resson")
    private String reason;

}
