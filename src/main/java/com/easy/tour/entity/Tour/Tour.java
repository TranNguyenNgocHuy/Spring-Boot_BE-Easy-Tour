package com.easy.tour.entity.Tour;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Table(name = "tour")
public class Tour {
    @Id
    @Column(name = "Tour_Code")
    private String tourCode;

    @Column(name = "Tour_Name")
    private String tourName;

    @Column(name = "Description")
    private String description;

    @Column(name = "Maximum_Size")
    private Integer maximumSize;
// @Column(name = "Creator")
// private String creator;
//
// @Column(name = "Create_Date")
// private Date createDate;
//
// @Column(name = "Approved_By")
// private String approvedBy;
//
// @Column(name = "Approval_Date")
// private Date approvalDate;

// @Column(name = "Approval_Status")
// @Enumerated(EnumType.STRING)
// private ApprovalStatus approvalStatus;
}
