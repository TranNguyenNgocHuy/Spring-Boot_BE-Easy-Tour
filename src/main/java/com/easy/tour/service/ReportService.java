package com.easy.tour.service;

import com.easy.tour.dto.ReportDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReportService extends BaseService<ReportDTO>{

    public List<ReportDTO> findAll();
    public List<ReportDTO> findByMonth(String month);

}
