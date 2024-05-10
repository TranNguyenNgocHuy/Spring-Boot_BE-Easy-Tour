package com.easy.tour.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode
@Data
public class TourDTO extends BaseObject{
    private String tourCode;
    private String tourName;
    private String description;
    private Integer maximumSize;
// private String creator;
// private Date createDate;
// private String approvedBy;
// private Date approvalDate;
// private ApprovalStatus approvalStatus = ApprovalStatus.PENDING_OP;
}
