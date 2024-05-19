package com.easy.tour.service;

import com.easy.tour.dto.DepartureDateDTO;

import java.util.List;

public interface DepartureDateService {
    DepartureDateDTO createDepartureDate(DepartureDateDTO departureDateDTO);

    List<DepartureDateDTO> getAllDepartureDate();
}
