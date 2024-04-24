package com.easy.tour.mapper;

import com.easy.tour.dto.PriceDTO;
import com.easy.tour.entity.Price.PriceDetail;
import org.springframework.stereotype.Service;

@Service
public class PriceDetailMapper extends  AbstractMapper<PriceDetail, PriceDTO> {
    public PriceDetailMapper() { super(PriceDetail.class, PriceDTO.class); }
}
