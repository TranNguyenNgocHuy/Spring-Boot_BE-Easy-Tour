package com.easy.tour.service;

import com.easy.tour.dto.PriceDTO;

import java.util.List;

public interface PriceService {
//    boolean updatePriceByTourCode(PriceDTO PriceDTO, String tourCode);
//
//    boolean deletePriceByTourCode(String tourCode);
//
//    PriceDTO findByTourCode(String tourCode);
//
    PriceDTO createPrice(PriceDTO priceDTO);

    List<PriceDTO> findAll();
}
