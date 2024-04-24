package com.easy.tour.entity.Price;

import com.easy.tour.entity.BaseEntity;
import lombok.*;

import jakarta.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Table(name = "price_detail")
public class PriceDetail extends BaseEntity {

    @Id
    @Column(name = "Price_Detail_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Coach")
    private BigDecimal Coach;

    @Column(name = "Main_Guider")
    private BigDecimal mainGuider;

    @Column(name = "Local_Guider")
    private BigDecimal localGuider;

    @Column(name = "Air_Ticket")
    private BigDecimal airTicket;

    @Column(name = "Food")
    private BigDecimal food;

    @Column(name = "Attraction")
    private BigDecimal attraction;

    @Column(name = "Hotel")
    private BigDecimal hotel;

    @Column(name = "Insurance")
    private BigDecimal insurance;

    @Column(name = "Tax")
    private BigDecimal tax;

    @Column(name = "Other_Price")
    private BigDecimal otherPrice;

    @Column(name = "VisaFee")
    private BigDecimal visaFee;

    @Column(name = "Adult")
    private BigDecimal adult;

    @Column(name = "Children")
    private BigDecimal children;

    @OneToOne
    @MapsId
    @JoinColumn(name = "Price_Id")
    private Price price;
}
