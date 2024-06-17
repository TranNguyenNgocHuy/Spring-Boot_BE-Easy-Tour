package com.easy.tour.entity.Tour;

import com.easy.tour.Enum.ApprovalStatus;
import com.easy.tour.Enum.PriceStatus;
import com.easy.tour.entity.Price.Price;
import com.easy.tour.entity.departure.DepartureDate;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "Approval_Status")
    @Enumerated(EnumType.STRING)
    private ApprovalStatus approvalStatus;

    @Column(name = "Price_Status")
    @Enumerated(EnumType.STRING)
    private PriceStatus priceStatus;

    @ManyToOne
    @JoinColumn(name = "Tour_Request_Id")
    private TourRequest tourRequest;

    @Column(name = "Img1")
    private String tourImg1;

    @Column(name = "Img2")
    private String tourImg2;

    @OneToOne(mappedBy = "tour", cascade = CascadeType.ALL, orphanRemoval = true)
    private Price price;

    @OneToOne(mappedBy = "tour", cascade = CascadeType.ALL, orphanRemoval = true)
    private TourApproval tourApproval;

    @OneToMany(mappedBy = "tour", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DepartureDate> departureDateList = new ArrayList<>();

//  Dương
    public void addDepartureDate(DepartureDate date) {
        departureDateList.add(date);
        date.setTour(this);
    }

    public void removeDepartureDate(DepartureDate date) {
        departureDateList.remove(date);
        date.setTour(this);
    }
}
