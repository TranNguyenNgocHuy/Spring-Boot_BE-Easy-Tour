package com.easy.tour.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode
@Data
public class RefundRequestDTO extends BaseObject{
    private Long refundRequestId;

    private String tourCode;

    private String customer;

    private Integer phoneNumber;

    private Integer refundAmount;

    private String reason;
}