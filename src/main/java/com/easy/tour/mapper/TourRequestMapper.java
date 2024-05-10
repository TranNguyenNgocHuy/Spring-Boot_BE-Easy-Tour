package com.easy.tour.mapper;


import com.easy.tour.dto.TourRequestDTO;
import com.easy.tour.entity.Tour.TourRequest;
import org.springframework.stereotype.Service;

@Service
public class TourRequestMapper extends AbstractMapper<TourRequest, TourRequestDTO> {

    public TourRequestMapper() {
        super(TourRequest.class, TourRequestDTO.class);
    }
}
