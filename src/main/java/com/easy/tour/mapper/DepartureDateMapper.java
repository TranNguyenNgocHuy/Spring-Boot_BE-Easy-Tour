package com.easy.tour.mapper;

import com.easy.tour.dto.DepartureDateDTO;
import com.easy.tour.dto.PriceDTO;
import com.easy.tour.entity.Price.PriceDetail;
import com.easy.tour.entity.departure.DepartureDate;
import com.easy.tour.service.DepartureDateService;
import org.springframework.stereotype.Service;

@Service
public class DepartureDateMapper extends AbstractMapper<DepartureDate, DepartureDateDTO>{
    public DepartureDateMapper() { super(DepartureDate.class, DepartureDateDTO.class); }
    }
