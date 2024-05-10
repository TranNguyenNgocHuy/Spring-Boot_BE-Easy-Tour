package com.easy.tour.repository;

import com.easy.tour.entity.Tour.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TourRepository extends JpaRepository<Tour, String> {
    boolean existsByTourCode(String tourCode);
    Tour findByTourCode(String tourCode);
}
