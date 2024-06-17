package com.easy.tour.controller;

import com.easy.tour.consts.ApiPath;
import com.easy.tour.dto.OrderDTO;
import com.easy.tour.response.OrderResponseDTO;
import com.easy.tour.service.Impl.OrderServiceImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class OrderController {
    @Autowired
    OrderServiceImpl service;

    @GetMapping(value = ApiPath.ORDER_GET_All)
    public ResponseEntity<OrderResponseDTO> getAllOrders() {
        OrderResponseDTO response = new OrderResponseDTO();
        try {
            List<OrderDTO> list = service.getAll();
            response.setList(list);
            response.setMessage("successful");
            response.setErrorCode(200);
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception ex) {
            response.setMessage("Error when getting all orders request: " + ex);
            response.setErrorCode(500);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = ApiPath.ORDER_GET_BY_ID)
    public ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable(value = "id") Integer id) {
        OrderResponseDTO response = new OrderResponseDTO();
        try {
            OrderDTO orderDTO = service.customGetById(id);
            System.out.println(orderDTO.getTourCode());
            System.out.println(orderDTO.getBookingDate());
            response.setData(orderDTO);
            response.setMessage("successful");
            response.setErrorCode(200);
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception ex) {
            response.setMessage("Error when getting order request: " + ex);
            response.setErrorCode(500);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = ApiPath.ORDER_CREATE)
    public ResponseEntity<OrderResponseDTO> createOrder(@Valid @RequestBody OrderDTO orderDTO) {
        OrderResponseDTO response = new OrderResponseDTO();
        try {
            System.out.println("booking date: " + orderDTO.getBookingDate());
            System.out.println("tourcode: " +orderDTO.getTourCode());

            OrderDTO result = service.customCreate(orderDTO);

            System.out.println("booking date: " + result.getBookingDate());
            System.out.println("tourcode: " +result.getTourCode());
            response.setData(result);
            response.setMessage("successful");
            response.setErrorCode(200);
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception ex) {
            response.setMessage("Error when getting order request: " + ex);
            response.setErrorCode(500);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = ApiPath.ORDER_UPDATE)
    public ResponseEntity<OrderResponseDTO> updateOrder(
            @Valid @RequestBody OrderDTO orderDto,
            @PathVariable(value = "id") Integer id
    ) {
        OrderResponseDTO response = new OrderResponseDTO();
        System.out.println(orderDto.getBookingDate());
        try {
            service.customUpdateById(orderDto, id);
            response.setMessage("successful");
            response.setErrorCode(200);
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception ex) {
            response.setMessage("Error when getting order request: " + ex);
            response.setErrorCode(500);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = ApiPath.ORDER_DELETE)
    public ResponseEntity<OrderResponseDTO> deleteOrder(
            @PathVariable(value = "id") Integer id
    ) {
        OrderResponseDTO response = new OrderResponseDTO();
        try {
            service.delete(id);
            response.setMessage("successfully delete order");
            response.setErrorCode(200);
            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception ex) {
            response.setMessage("Error when getting order request: " + ex);
            response.setErrorCode(500);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
