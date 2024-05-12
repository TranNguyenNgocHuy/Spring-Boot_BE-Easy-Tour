package com.easy.tour.service.Impl;

import com.easy.tour.dto.PriceDTO;
import com.easy.tour.dto.TourDTO;
import com.easy.tour.dto.TourRequestDTO;
import com.easy.tour.entity.Price.Price;
import com.easy.tour.entity.Tour.Tour;
import com.easy.tour.entity.Tour.TourRequest;
import com.easy.tour.mapper.AbstractMapper;
import com.easy.tour.mapper.TourMapper;
import com.easy.tour.mapper.TourRequestMapper;
import com.easy.tour.repository.TourRepository;
import com.easy.tour.repository.TourRequestRepository;
import com.easy.tour.service.TourService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class TourServiceImpl extends AbstractBaseServiceImpl<TourDTO>
        implements TourService {
    @Autowired
    TourRepository tourRepository;

    @Autowired
    TourMapper tourMapper;

    @Autowired
    TourRequestRepository tourRequestRepository;
    @Autowired
    TourRequestServiceImpl tourRequestService;

    @Autowired
    TourRequestMapper tourRequestMapper;

    public TourServiceImpl() {
        super.setMapper(new TourMapper());
    }

    @Override
    public void setRepository() {
        AbstractBaseServiceImpl.setRepository(tourRepository);
    }

    @Override
    public TourDTO findByTourCode(String tourCode) {
        Tour tour = tourRepository.findByTourCode(tourCode);

        if (tour != null) {

            TourDTO tourDto = tourMapper.convertEntityToDTO(tour);
            log.info("tour: {}" + tourDto);
            return tourDto;
        }
        return null;
    }

    @Override
    public boolean updateTourByTourCode(TourDTO tourDTO, String tourCode) {
        try {
            Tour tour = tourRepository.findByTourCode(tourCode);
            tourMapper.mapDTOToEntity(tourDTO, tour);
            tourRepository.save(tour);
            return true;
        } catch (Exception e) {
            log.error("Error when update tour: " + e);
            return false;
        }
    }

    @Override
    public boolean deleteTourByTourCode(String tourCode) {
        Tour tour = tourRepository.findByTourCode(tourCode);
        if (tour != null) {
            tourRepository.delete(tour);
            return true;
        }
        return false;
    }

    public TourDTO createTour(TourDTO tourDTO) {

        Tour tour = tourMapper.convertDTOToEntity(tourDTO);

        TourRequest tourRequest = tourRequestRepository.findByUuid(UUID.fromString(tourDTO.getTourRequestCode().trim()));


        tour.setTourRequest(tourRequest);
        System.out.println(tour);

        return tourMapper.convertEntityToDTO(tourRepository.save(tour));
    }

}
