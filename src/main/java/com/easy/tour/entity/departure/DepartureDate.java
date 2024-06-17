package com.easy.tour.entity.departure;

import com.easy.tour.entity.BaseEntity;
import com.easy.tour.entity.Order.Order;
import com.easy.tour.entity.Tour.Tour;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

//    @ManyToOne
//    @JoinColumn(name = "tour_code")
//    private Tour tour;

// Dương
    @ManyToOne(fetch = FetchType.LAZY)
    private Tour tour;

    @OneToMany(
            mappedBy = "departureDate",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Order> orderList = new ArrayList<>();

    public void addOrder(Order order) {
        orderList.add(order);
        order.setDepartureDate(this);
    }

    public void removeOrder(Order order) {
        orderList.remove(order);
        order.setDepartureDate(null);
    }
}
