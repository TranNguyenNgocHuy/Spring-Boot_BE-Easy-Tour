package com.easy.tour.repository;

import com.easy.tour.entity.Price.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {
//    boolean existsByTourCode(String tourCode);
//
//    Price findByTourCode(String tourCode);
}
