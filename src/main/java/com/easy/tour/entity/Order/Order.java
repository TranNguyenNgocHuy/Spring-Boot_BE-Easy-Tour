package com.easy.tour.entity.Order;

import com.easy.tour.Enum.ApprovalStatus;
import com.easy.tour.Enum.Gender;
import com.easy.tour.entity.BaseEntity;
import com.easy.tour.entity.departure.DepartureDate;
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
@Table(name = "orders")
@EqualsAndHashCode
public class Order extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;

    @Column(name = "Approval_Status")
    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(nullable = false)
    private String address;

    private int adult;

    private int children;

    private int pet;

    @Column(name = "payment_method", nullable = false)
    private String paymentMethod;

    @ManyToOne(fetch = FetchType.LAZY)
    private DepartureDate departureDate;

}
