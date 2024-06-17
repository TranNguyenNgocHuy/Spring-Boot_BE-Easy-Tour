package com.easy.tour.controller;

import com.easy.tour.consts.ApiPath;
import com.easy.tour.dto.RefundRequestDTO;
import com.easy.tour.response.RefundRequestResponseDTO;
import com.easy.tour.response.ResponseDTO;
import com.easy.tour.service.Impl.RefundRequestServiceImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class RefundRequestController {

    @Autowired
    private RefundRequestServiceImpl service;

    @GetMapping(value = ApiPath.REFUND_REQUEST_GET_ALL)
    public ResponseEntity<?> getAllRefundRequest() {
        RefundRequestResponseDTO response = new RefundRequestResponseDTO();
        try {
            List<RefundRequestDTO> list = service.getAll();
            response.setList(list);
            response.setMessage("successful");
            response.setErrorCode(200);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            response.setMessage("Error when getting all refund request: " + ex);
            response.setErrorCode(500);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = ApiPath.REFUND_REQUEST_GET_BY_ID)
    public ResponseEntity<?> getRefundRequestById(
            @PathVariable(value = "id") Long id){
        RefundRequestResponseDTO response = new RefundRequestResponseDTO();
        try {
            RefundRequestDTO refundRequestDTO = service.getByID(id);
            response.setData(refundRequestDTO);
            response.setMessage("Successful");
            response.setErrorCode(200);
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception ex) {
            response.setMessage("Error when getting refund request:" + ex);
            response.setErrorCode(500);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = ApiPath.REFUND_REQUEST_CREATE)
    public ResponseEntity<ResponseDTO> create(@RequestBody RefundRequestDTO refundRequestDTO) {
        RefundRequestResponseDTO response = new RefundRequestResponseDTO();
        try {
            RefundRequestDTO result = service.create(refundRequestDTO);
            response.setData(result);
            response.setMessage("Successfully created new refund requets detail");
            response.setErrorCode(200);
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception ex) {
            response.setMessage("Error when creating new refund request: " + ex);
            response.setErrorCode(500);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping(value = ApiPath.REFUND_REQUEST_UPDATE)
    public ResponseEntity<RefundRequestResponseDTO> updateReundRequest(
            @Valid @RequestBody RefundRequestDTO refundRequestDTO,
            @PathVariable(value = "id") Long id
    ) {
        RefundRequestResponseDTO response = new RefundRequestResponseDTO();
        try {
            service.updateById(refundRequestDTO, id);
            response.setMessage("successful");
            response.setErrorCode(200);
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception ex) {
            response.setMessage("Error when getting refund request: " + ex);
            response.setErrorCode(500);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = ApiPath.REFUND_REQUEST_DELETE + "/{id}")
    @ResponseBody
    public ResponseEntity<?> delete(@PathVariable(value = "id") String id) {
        RefundRequestResponseDTO response = new RefundRequestResponseDTO();
        try {
            Long refund = Long.parseLong(id);
            service.delete(refund);
            response.setMessage("Successfully deleted entity");
            response.setErrorCode(200);
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception ex) {
            response.setMessage("Error when deleting refund request:" + ex);
            response.setErrorCode(500);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
