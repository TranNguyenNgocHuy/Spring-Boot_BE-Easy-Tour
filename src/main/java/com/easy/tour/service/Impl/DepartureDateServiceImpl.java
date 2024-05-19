package com.easy.tour.service.Impl;

import com.easy.tour.dto.DepartureDateDTO;
import com.easy.tour.entity.Tour.Tour;
import com.easy.tour.entity.departure.DepartureDate;
import com.easy.tour.mapper.DepartureDateMapper;
import com.easy.tour.repository.DepartureDateRepository;
import com.easy.tour.repository.TourRepository;
import com.easy.tour.service.DepartureDateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class DepartureDateServiceImpl extends AbstractBaseServiceImpl<DepartureDateDTO> implements DepartureDateService {
    @Autowired
    DepartureDateMapper departureDateMapper;

    @Autowired
    DepartureDateRepository departureDateRepository;

    @Autowired
    TourRepository tourRepository;

    public DepartureDateServiceImpl() {
        super.setMapper(new DepartureDateMapper());
    }

    @Override
    public void setRepository() {
        AbstractBaseServiceImpl.setRepository(departureDateRepository);
    }

    @Override
    public DepartureDateDTO createDepartureDate(DepartureDateDTO departureDateDTO) {
        DepartureDate departureDate = departureDateMapper.convertDTOToEntity(departureDateDTO);

        Tour tour = tourRepository.findByTourCode(departureDateDTO.getTourCode());

        departureDate.setTour(tour);

        return departureDateMapper.convertEntityToDTO(departureDateRepository.save(departureDate));
    }

    @Override
    public List<DepartureDateDTO> getAllDepartureDate() {
        try {
            List<DepartureDate> departureDateList = departureDateRepository.findAll();
            List<DepartureDateDTO> departureDateDTOList = new ArrayList<>();
            for (DepartureDate departureDate : departureDateList) {
                DepartureDateDTO departureDateDTO = departureDateMapper.convertEntityToDTO(departureDate);
                departureDateDTO.setTourCode(departureDate.getTour().getTourCode());
                departureDateDTOList.add(departureDateDTO);
            }
            return departureDateDTOList;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }


}
