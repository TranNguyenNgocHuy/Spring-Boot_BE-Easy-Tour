package com.easy.tour.entity.Tour;

import com.easy.tour.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tour_approval")
public class TourApproval extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Approval_Tour_Id")
    private Long approvalTourId;

    @OneToOne
    @JoinColumn(name = "Tour_Code", referencedColumnName = "Tour_Code")
    private Tour tour;
}
