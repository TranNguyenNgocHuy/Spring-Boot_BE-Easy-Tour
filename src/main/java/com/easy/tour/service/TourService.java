package com.easy.tour.service;

import com.easy.tour.dto.BaseObject;
import com.easy.tour.dto.TourDTO;

import java.util.List;

public interface TourService {
    TourDTO findByTourCode(String tourCode);

    boolean deleteTourByTourCode(String tourCode);

    boolean updateTourByTourCode(TourDTO tourDTO, String tourCode);

    List<String> tourCodeWithOutPrice();

    List<TourDTO> getAllProduct();
}
