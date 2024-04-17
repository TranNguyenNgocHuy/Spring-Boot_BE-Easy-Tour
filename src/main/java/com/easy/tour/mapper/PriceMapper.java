package com.easy.tour.mapper;

import com.easy.tour.dto.PriceDTO;
import com.easy.tour.entity.Price.Price;
import org.springframework.stereotype.Service;

@Service
public class PriceMapper extends AbstractMapper<Price, PriceDTO> {
    public PriceMapper() {
        super(Price.class, PriceDTO.class);
    }
}
