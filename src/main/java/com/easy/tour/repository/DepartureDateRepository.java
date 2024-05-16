package com.easy.tour.repository;

import com.easy.tour.entity.Price.Price;
import com.easy.tour.entity.departure.DepartureDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface DepartureDateRepository extends JpaRepository<DepartureDate, Integer> {
}
