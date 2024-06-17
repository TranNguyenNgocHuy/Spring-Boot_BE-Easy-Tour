package com.easy.tour.mapper;

import com.easy.tour.dto.ReportDTO;
import com.easy.tour.entity.Report.Report;
import org.springframework.stereotype.Service;

@Service
public class ReportMapper extends  AbstractMapper<Report, ReportDTO> {
    public ReportMapper() { super(Report.class, ReportDTO.class); }
}
