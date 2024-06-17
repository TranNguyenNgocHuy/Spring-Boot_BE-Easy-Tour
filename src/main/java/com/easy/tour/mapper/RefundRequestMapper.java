package com.easy.tour.mapper;


import com.easy.tour.dto.RefundRequestDTO;
import com.easy.tour.entity.Refund.RefundRequest;
import org.springframework.stereotype.Service;

@Service
public class RefundRequestMapper extends AbstractMapper<RefundRequest, RefundRequestDTO>{

    public RefundRequestMapper() { super(RefundRequest.class, RefundRequestDTO.class); }
}
