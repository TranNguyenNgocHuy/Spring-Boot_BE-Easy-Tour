package com.easy.tour.repository;

import com.easy.tour.entity.Tour.Tour;
import com.easy.tour.entity.Tour.TourRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TourRequestRepository extends JpaRepository<TourRequest, UUID> {

    TourRequest findByUuid(UUID uuid);

}
