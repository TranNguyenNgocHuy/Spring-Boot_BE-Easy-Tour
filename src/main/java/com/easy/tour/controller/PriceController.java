package com.easy.tour.controller;

import com.easy.tour.consts.ApiPath;
import com.easy.tour.dto.PriceDTO;
import com.easy.tour.response.PriceResponseDTO;
import com.easy.tour.service.Impl.PriceServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class PriceController {

    @Autowired
    PriceServiceImpl service;


    @GetMapping(value = ApiPath.PRICE_GET_All)
    public ResponseEntity<?> getAllPriceList() {
        PriceResponseDTO response = new PriceResponseDTO();
        try {
            List<PriceDTO> priceDTOList = service.findAll();
            response.setMessage("Successfully retrieved All Price");
            response.setErrorCode(200);
            response.setList(priceDTOList);
            System.out.println(priceDTOList);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setMessage("Error when get all Price list , Please try again");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//
//    @GetMapping(value = ApiPath.PRICE_GET_BY_TOUR_CODE)
//    public ResponseEntity<?> getByTourCode(@PathVariable("tourCode") String tourCode) {
//        PriceResponseDTO response = new PriceResponseDTO();
//        try {
//            PriceDTO priceDto = service.findByTourCode(tourCode);
//            if (priceDto != null) {
//                response.setMessage("Successfully to get Price by tour code: " + tourCode);
//                response.setErrorCode(200);
//                response.setData(priceDto);
//                return  new ResponseEntity<>( response, HttpStatus.OK);
//            } else {
//                response.setMessage("Price with tour code " + tourCode + " does not exist!");
//                response.setErrorCode(404);
//                return  new ResponseEntity<>( response, HttpStatus.NOT_FOUND);
//            }
//        } catch (Exception e) {
//            response.setMessage("Error when get price with by code: " + tourCode);
//            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
    @PostMapping(value = ApiPath.PRICE_CREATE)
    ResponseEntity<?> createPrice(@RequestBody PriceDTO priceDTO) {
        PriceResponseDTO response = new PriceResponseDTO();
        try {
            PriceDTO createPrice = service.createPrice(priceDTO);
            if(createPrice != null) {
                response.setMessage("Success created Price with tour code: " + priceDTO.getTourCode());
                response.setErrorCode(200);
                response.setData(createPrice);
                return new ResponseEntity(response, HttpStatus.OK);
            } else {
                response.setMessage("Tour Code " + priceDTO.getTourCode() + " already exist");
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
//
//    @PutMapping(value = ApiPath.PRICE_UPDATE)
//    ResponseEntity<?> updatePrice(@RequestBody PriceDTO updatePriceDTO, @PathVariable("tourCode") String tourCode) {
//        PriceResponseDTO response = new PriceResponseDTO();
//        try {
//            boolean updateResult = service.updatePriceByTourCode(updatePriceDTO, tourCode);
//            if(updateResult) {
//                response.setMessage("Update Price Successfully");
//                response.setErrorCode(200);
//                return  new ResponseEntity<>(response, HttpStatus.OK);
//            } else {
//                response.setMessage("Failed to update Price");
//                response.setErrorCode(400); // Bad Request
//                return new ResponseEntity<>(response, HttpStatus.NOT_IMPLEMENTED);
//            }
//        } catch (Exception e) {
//            log.trace(String.valueOf(e));
//                response.setMessage("Error when update Price , Please try again");
//                response.setErrorCode(500);
//                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @DeleteMapping(value = ApiPath.PRICE_DELETE)
//    ResponseEntity<?> deletePrice(@PathVariable("tourCode") String tourCode) {
//        PriceResponseDTO response = new PriceResponseDTO();
//        try{
//            boolean deleteResult = service.deletePriceByTourCode(tourCode);
//            if(deleteResult) {
//                response.setMessage("Successfully deleted Price with tour code: " + tourCode);
//                response.setErrorCode(200);
//                return new ResponseEntity<>(response, HttpStatus.OK);
//            } else {
//                response.setMessage("Failed to deleted Price with tour code: " + tourCode);
//                response.setErrorCode(400);
//                return new ResponseEntity<>(response, HttpStatus.NOT_IMPLEMENTED);
//            }
//        } catch (Exception e) {
//            response.setMessage("Error when deleted Price, Please try again");
//            response.setErrorCode(500);
//            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
}
