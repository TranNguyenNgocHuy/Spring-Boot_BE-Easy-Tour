package com.easy.tour.service;

import com.easy.tour.dto.PriceDTO;

public interface PriceService extends BaseService<PriceDTO> {
    boolean updatePriceByTourCode(PriceDTO PriceDTO, String tourCode);

    boolean deletePriceByTourCode(String tourCode);

    PriceDTO findByTourCode(String tourCode);

}
