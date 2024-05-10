package com.easy.tour.controller;

import com.easy.tour.consts.ApiPath;
import com.easy.tour.dto.TourRequestDTO;
import com.easy.tour.response.ResponseDTO;
import com.easy.tour.response.TourRequestResponseDTO;
import com.easy.tour.response.TourResponseDTO;
import com.easy.tour.service.Impl.TourRequestServiceImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
public class TourRequestController {

    @Autowired
    private TourRequestServiceImpl service;

    @GetMapping(value = ApiPath.TOUR_REQUEST_GET_All)
    public ResponseEntity<?> getAllTourDetail() {
        TourRequestResponseDTO response = new TourRequestResponseDTO();
        try {
            List<TourRequestDTO> list = service.getAll();
            response.setList(list);
            response.setMessage("successful");
            response.setErrorCode(200);
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception ex) {
            response.setMessage("Error when getting all tour request: " + ex);
            response.setErrorCode(500);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = ApiPath.TOUR_REQUEST_GET_BY_UUID)
    public ResponseEntity<?> getTourDetailById(
            @PathVariable(value = "uuid") UUID uuid){
        TourRequestResponseDTO response = new TourRequestResponseDTO();
        try {
            TourRequestDTO tourRequestDTO = service.getByID(uuid);
            response.setData(tourRequestDTO);
            response.setMessage("Successful");
            response.setErrorCode(200);
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception ex) {
            response.setMessage("Error when getting tour request:" + ex);
            response.setErrorCode(500);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = ApiPath.TOUR_REQUEST_CREATE)
    public ResponseEntity<ResponseDTO> create(@RequestBody TourRequestDTO tourRequestDTO) {
        TourRequestResponseDTO response = new TourRequestResponseDTO();
        try {
            TourRequestDTO result = service.create(tourRequestDTO);
            response.setData(result);
            response.setMessage("Successfully created new tour detail");
            response.setErrorCode(200);
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception ex) {
            response.setMessage("Error when creating new tour request: " + ex);
            response.setErrorCode(500);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = ApiPath.TOUR_REQUEST_UPDATE)
    public ResponseEntity<?> update(
            @PathVariable(value = "uuid") UUID uuid,
            @Valid @RequestBody TourRequestDTO tourRequestDTO) {
        TourRequestResponseDTO response = new TourRequestResponseDTO();
        try {
            tourRequestDTO.setUuid(uuid);
            TourRequestDTO result = service.update(tourRequestDTO);
            response.setData(result);
            response.setMessage("Successfully updated tour request");
            response.setErrorCode(200);
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception ex) {
            response.setMessage("Error when update tour request:" + ex);
            response.setErrorCode(500);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = ApiPath.TOUR_REQUEST_DELETE)
    public ResponseEntity<?> delete(@PathVariable(value = "uuid") UUID uuid) {
        TourRequestResponseDTO response = new TourRequestResponseDTO();
        try {
            service.delete(uuid);
            response.setMessage("Successfully deleted entity");
            response.setErrorCode(200);
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception ex) {
            response.setMessage("Error when deleting tour request:" + ex);
            response.setErrorCode(500);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
