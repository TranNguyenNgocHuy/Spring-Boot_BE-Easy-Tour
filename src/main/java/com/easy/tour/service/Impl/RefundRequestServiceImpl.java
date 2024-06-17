package com.easy.tour.service.Impl;

import com.easy.tour.dto.RefundRequestDTO;
import com.easy.tour.entity.Refund.RefundRequest;
import com.easy.tour.mapper.AbstractMapper;
import com.easy.tour.repository.RefundRequestRepository;
import com.easy.tour.service.RefundRequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class RefundRequestServiceImpl extends AbstractBaseServiceImpl<RefundRequestDTO>
        implements RefundRequestService {

    @Autowired
    RefundRequestRepository repository;

    public RefundRequestServiceImpl() {
        AbstractMapper mapper = new AbstractMapper(RefundRequest.class, RefundRequestDTO.class);
        super.setMapper(mapper);
    }

    @Override
    public void setRepository() {
        AbstractBaseServiceImpl.setRepository(this.repository);
    }

    @Override
    public boolean updateById(RefundRequestDTO refundRequestDTO, Long id) {
        return false;
    }
}
