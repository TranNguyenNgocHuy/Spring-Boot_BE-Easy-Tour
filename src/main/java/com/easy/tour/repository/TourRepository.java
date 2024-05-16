package com.easy.tour.repository;

import com.easy.tour.entity.Tour.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TourRepository extends JpaRepository<Tour, String> {
    boolean existsByTourCode(String tourCode);
    Tour findByTourCode(String tourCode);

    @Query("SELECT t.tourCode FROM Tour t WHERE t.price IS NULL")
    List<String> findTourCodesWithoutPrice();

    @Query("SELECT t.tourCode FROM Tour t")
    List<String> findTourCodes();
}
