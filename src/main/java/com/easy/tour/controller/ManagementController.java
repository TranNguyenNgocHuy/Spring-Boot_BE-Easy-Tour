package com.easy.tour.controller;

import com.easy.tour.consts.ApiPath;
import com.easy.tour.dto.ReportDTO;
import com.easy.tour.response.ReportResponseDTO;
import com.easy.tour.service.Impl.ReportServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class ManagementController {
//    @Autowired
//    private JReportService reportService;

    @Autowired
    ReportServiceImpl service;
    @GetMapping(ApiPath.REPORT_GET_ALL)
    public ResponseEntity<?>  getAllReport() {
        ReportResponseDTO response = new ReportResponseDTO();
        try{
            List<ReportDTO> reportsList = (List<ReportDTO>) service.getAll();
            response.setMessage("Successfully retrieved All Report");
            response.setErrorCode(200);
            response.setList(reportsList);
            System.out.println(reportsList);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e) {
            response.setMessage("Error when get all Price list , Please try again");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(ApiPath.REPORT_GET_BY_MONTH+ "/{month}")
    public ResponseEntity<?>  getAllReport(@PathVariable String month) {
        ReportResponseDTO response = new ReportResponseDTO();
        try{
            List<ReportDTO> reportsList = (List<ReportDTO>) service.findByMonth(month);
            response.setMessage("Successfully retrieved All Report");
            response.setErrorCode(200);
            response.setList(reportsList);
            System.out.println(reportsList);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception e) {
            response.setMessage("Error when get all Price list , Please try again");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

//    @GetMapping(ApiPath.REPORT_PDF)
//    public ResponseEntity<?> exportPDF(HttpServletResponse response) throws IOException, JRException{
//        ReportResponseDTO reportResponseDTO = new ReportResponseDTO();
////        response.setContentType("application/pdf");
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
//        String currentDateTime = dateFormat.format(new Date());
//        String headerKey = "Content-Disposition";
//        String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
////        response.setHeader(headerKey, headerValue);
//
//        if (reportService.exportJasperReportPDF(response)){
//            return new ResponseEntity<>(reportResponseDTO, HttpStatus.OK);
//        }
//        return ResponseEntity.ok("Oh no has something wrong");
//    }
}
