package com.easy.tour.dto;

import com.easy.tour.Enum.ApprovalStatus;
import com.easy.tour.Enum.Gender;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode
@Data
public class OrderDTO {

    private Integer orderId;

    private ApprovalStatus approvalStatus;

    private String firstName;

    private String lastName;

    private Gender gender = Gender.Male;

    private Long phoneNumber;

    private String email;

    private String address;

    private int adult;

    private int children;

    private int pet;

    private String paymentMethod;

    private String tourCode;

    private LocalDate bookingDate;

//    private Long totalFee;
}