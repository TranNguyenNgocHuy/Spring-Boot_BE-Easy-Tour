package com.easy.tour.service.Impl;

import com.easy.tour.dto.TourRequestDTO;
import com.easy.tour.mapper.PriceMapper;
import com.easy.tour.mapper.TourRequestMapper;
import com.easy.tour.repository.TourRequestRepository;
import com.easy.tour.service.TourRequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TourRequestServiceImpl extends AbstractBaseServiceImpl<TourRequestDTO>
        implements TourRequestService {

    @Autowired
    TourRequestRepository repository;

    @Autowired
    TourRequestMapper tourRequestMapper;

    public TourRequestServiceImpl() {
        super.setMapper(new TourRequestMapper());
    }

    @Override
    public void setRepository() {
        AbstractBaseServiceImpl.setRepository(repository);
    }
}
