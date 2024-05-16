package com.easy.tour.controller;

import com.easy.tour.consts.ApiPath;
import com.easy.tour.dto.BaseObject;
import com.easy.tour.dto.DepartureDateDTO;
import com.easy.tour.dto.TourDTO;
import com.easy.tour.entity.Tour.Tour;
import com.easy.tour.mapper.TourMapper;
import com.easy.tour.repository.TourRequestRepository;
import com.easy.tour.response.DepartureDateResponseDTO;
import com.easy.tour.response.ResponseDTO;
import com.easy.tour.response.TourResponseDTO;
import com.easy.tour.service.DepartureDateService;
import com.easy.tour.service.Impl.TourServiceImpl;
import com.easy.tour.service.TourService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
public class TourController {

    @Autowired
    TourServiceImpl tourService;
    @Autowired
    TourMapper tourMapper;

    @Autowired
    TourRequestRepository tourRequestRepository;

    @Autowired
    DepartureDateService departureDateService;


    @GetMapping(value = ApiPath.TOUR_GET_All)
    public ResponseEntity<?> getAllTourList() {
        TourResponseDTO response = new TourResponseDTO();
        try {
            List<TourDTO> tourDTOList = tourService.getAll();
            response.setMessage("Successfully retrieved All Tour");
            response.setErrorCode(200);
            response.setList(tourDTOList);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setMessage("Error when get all Tour list , Please try again");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = ApiPath.TOUR_GET_BY_TOUR_CODE)
    public ResponseEntity<?> getByTourCode(@PathVariable("tourCode") String tourCode) {
        TourResponseDTO response = new TourResponseDTO();
        try {
            TourDTO tourDto = tourService.findByTourCode(tourCode);
            if (tourDto != null) {
                response.setMessage("Successfully to get Tour by tour code: " + tourCode);
                response.setErrorCode(200);
                response.setData(tourDto);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.setMessage("Tour with tour code " + tourCode + " does not exist!");
                response.setErrorCode(404);
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            response.setMessage("Error when get tour with by code: " + tourCode);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = ApiPath.TOUR_CREATE)
    ResponseEntity<?> createTour(@RequestBody TourDTO tourDTO) {
        TourResponseDTO response = new TourResponseDTO();
        try {
            TourDTO createdTour = tourService.createTour(tourDTO);
            if (createdTour != null) {
                response.setMessage("Success created Tour with tour code: " + tourDTO.getTourCode());
                response.setErrorCode(200);
                response.setData(createdTour);
                return new ResponseEntity(response, HttpStatus.OK);

            } else {
                response.setMessage("Tour Code " + tourDTO.getTourCode() + " already exist");
                response.setErrorCode(400);
                return new ResponseEntity<>(response, HttpStatus.NOT_IMPLEMENTED);
            }
        } catch (Exception e) {
            log.trace(String.valueOf(e));
            response.setMessage("Error when creating Tour, Please try again");
            response.setErrorCode(500);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = ApiPath.TOUR_UPDATE)
    ResponseEntity<?> updateTour(@RequestBody TourDTO updateTourDTO, @PathVariable("tourCode") String tourCode) {
        TourResponseDTO response = new TourResponseDTO();
        try {
            boolean updateResult = tourService.updateTourByTourCode(updateTourDTO, tourCode);
            if (updateResult) {
                response.setMessage("Update Tour Successfully");
                response.setErrorCode(200);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.setMessage("Failed to update Tour");
                response.setErrorCode(400); // Bad Request
                return new ResponseEntity<>(response, HttpStatus.NOT_IMPLEMENTED);
            }
        } catch (Exception e) {
            log.trace(String.valueOf(e));
            response.setMessage("Error when update Tour , Please try again");
            response.setErrorCode(500);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = ApiPath.TOUR_DELETE)
    ResponseEntity<?> deleteTour(@PathVariable("tourCode") String tourCode) {
        TourResponseDTO response = new TourResponseDTO();

        log.info("delete tour: {}", tourCode);
        try {
            boolean deleteResult = tourService.deleteTourByTourCode(tourCode);
            if (deleteResult) {
                response.setMessage("Successfully deleted Tour with tour code: " + tourCode);
                response.setErrorCode(200);
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.setMessage("Failed to deleted Tour with tour code: " + tourCode);
                response.setErrorCode(400);
                return new ResponseEntity<>(response, HttpStatus.NOT_IMPLEMENTED);
            }
        } catch (Exception e) {
            log.trace(String.valueOf(e.getMessage()));
            response.setMessage("Error when deleted Tour, Please try again");
            response.setErrorCode(500);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = ApiPath.TOUR_NON_PRICE_GET_ALL)
    public ResponseEntity<?> getAllTourNoPrice() {
        ResponseDTO response = new TourResponseDTO();
        try {
            List<String> tourCodeList = tourService.tourCodeWithOutPrice();
            response.setMessage("Successfully retrieved All tourCode without price");
            response.setErrorCode(200);
            response.setList(tourCodeList);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setMessage("Error when get all tourCode without price , Please try again");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = ApiPath.TOUR_ONLY_GET_ALL)
    public ResponseEntity<?> getAllTourOnlyTourCode() {
        ResponseDTO response = new TourResponseDTO();
        try {
            List<String> tourCodeList = tourService.findTourCodes();
            response.setMessage("Successfully retrieved All tourCode");
            response.setErrorCode(200);
            response.setList(tourCodeList);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setMessage("Error when get all tourCode, Please try again");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = ApiPath.TOUR_GET_ALL_PRODUCT)
    public ResponseEntity<?> showAllClientWeb() {
        TourResponseDTO response = new TourResponseDTO();
        try {
            List<TourDTO> productList = tourService.getAllProduct();
            response.setMessage("Successfully retrieved all tour product");
            response.setErrorCode(200);
            response.setList(productList);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setMessage("Error when get all tour Product , Please try again");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
