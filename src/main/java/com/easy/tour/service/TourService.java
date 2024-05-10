package com.easy.tour.service;

import com.easy.tour.dto.TourDTO;

public interface TourService {
    TourDTO findByTourCode(String tourCode);

    boolean deleteTourByTourCode(String tourCode);

    boolean updateTourByTourCode(TourDTO tourDTO, String tourCode);
}
