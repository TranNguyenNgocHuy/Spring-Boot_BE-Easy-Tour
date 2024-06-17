package com.easy.tour.service.Impl;

import com.easy.tour.dto.ReportDTO;
import com.easy.tour.entity.Report.Report;
import com.easy.tour.mapper.ReportMapper;
import com.easy.tour.repository.ReportRepository;
import com.easy.tour.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ReportServiceImpl extends AbstractBaseServiceImpl<ReportDTO>  implements ReportService{

    @Autowired
    ReportRepository repository;
    @Autowired
    ReportMapper reportMapper;

    public ReportServiceImpl() {
        super.setMapper(new ReportMapper());
    }
    @Override
    public void setRepository() {
        AbstractBaseServiceImpl.setRepository(repository);
    }

    @Override
    public List<ReportDTO> findAll() {
        List<Report> reportListeList = repository.findAll();
        return reportListeList == null ? new ArrayList<>()
                : reportListeList.stream().map(entity -> {
            ReportDTO reportDTO = reportMapper.convertEntityToDTO(entity);
            return reportDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public List<ReportDTO> findByMonth(String month) {
        List<Report> reportListeList = repository.findByMonth(month);
        return reportListeList == null ? new ArrayList<>()
                : reportListeList.stream().map(entity -> {
            ReportDTO reportDTO = reportMapper.convertEntityToDTO(entity);
            return reportDTO;
        }).collect(Collectors.toList());
    }


}
