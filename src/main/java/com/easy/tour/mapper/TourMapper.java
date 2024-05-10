package com.easy.tour.mapper;

import com.easy.tour.dto.TourDTO;
import com.easy.tour.entity.Tour.Tour;
import org.springframework.stereotype.Service;

@Service
public class TourMapper extends AbstractMapper<Tour, TourDTO> {
    public TourMapper() {
        super(Tour.class, TourDTO.class);
    }
}
