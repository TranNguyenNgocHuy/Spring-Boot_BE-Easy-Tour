package com.easy.tour.entity.departure;

import com.easy.tour.entity.BaseEntity;
import com.easy.tour.entity.Tour.Tour;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Table(name = "departure_date")
public class DepartureDate extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "departure_date")
    private LocalDate departureDate;

    @ManyToOne
    @JoinColumn(name = "tour_code")
    private Tour tour;
}
