package com.easy.tour.service;

import com.easy.tour.dto.RefundRequestDTO;
import org.springframework.stereotype.Service;

@Service
public interface RefundRequestService {
    boolean updateById(RefundRequestDTO refundRequestDTO, Long id);
}
