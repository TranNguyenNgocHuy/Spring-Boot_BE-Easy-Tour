package com.easy.tour.controller;

import com.easy.tour.consts.ApiPath;
import com.easy.tour.dto.DepartureDateDTO;
import com.easy.tour.dto.PriceDTO;
import com.easy.tour.dto.TourDTO;
import com.easy.tour.entity.Tour.Tour;
import com.easy.tour.response.DepartureDateResponseDTO;
import com.easy.tour.response.PriceResponseDTO;
import com.easy.tour.response.TourResponseDTO;
import com.easy.tour.service.Impl.DepartureDateServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@Slf4j
public class DepartureDateController {

    @Autowired
    DepartureDateServiceImpl departureDateService;

    @PostMapping(value = ApiPath.DEPARTURE_DATE_CREATE)
    ResponseEntity<?> createDepartureDate(@RequestBody DepartureDateDTO departureDateDTO) {
        DepartureDateResponseDTO response = new DepartureDateResponseDTO();
        try {
            DepartureDateDTO createdDepartureDate = departureDateService.createDepartureDate(departureDateDTO);
            if(createdDepartureDate != null) {
                response.setMessage("Success created DepartureDate with tour code: " + departureDateDTO.getTourCode());
                response.setErrorCode(200);
                response.setData(createdDepartureDate);
                return new ResponseEntity(response, HttpStatus.OK);
            } else {
                response.setMessage("Tour Code " + departureDateDTO.getTourCode() + " already exist");
                response.setErrorCode(401);
                return new ResponseEntity<>(response, HttpStatus.NOT_IMPLEMENTED);
            }
        } catch (Exception e) {
            log.trace(String.valueOf(e));
            response.setMessage("Error when creating Price, Please try again");
            response.setErrorCode(500);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = ApiPath.DEPARTURE_DATE_GET_ALL)
    public ResponseEntity<?> getAllDepartureDateList() {
        DepartureDateResponseDTO response = new DepartureDateResponseDTO();
        Tour tour = new Tour();
        try {
            List<DepartureDateDTO> departureDateDTOList = departureDateService.getAllDepartureDate();
            Collections.reverse(departureDateDTOList);
            response.setMessage("Successfully retrieved All departureDate");
            response.setErrorCode(200);
            response.setList(departureDateDTOList);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setMessage("Error when get all DepartureDate list , Please try again");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
