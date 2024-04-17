package com.easy.tour.controller;

import com.easy.tour.consts.ApiPath;
import com.easy.tour.dto.UserDTO;
import com.easy.tour.response.UserResponseDTO;
import com.easy.tour.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class UserController {
    @Autowired
    UserService service;

    @GetMapping(value = ApiPath.USER_GET_ALL)
//    @RequestMapping(value = ApiPath.USER_GET_ALL,
//            consumes = MediaType.APPLICATION_JSON_VALUE,
//            produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
    public ResponseEntity<?> getAllUsers() {
        UserResponseDTO response = new UserResponseDTO();
        try {
            List<UserDTO> list = service.findAll();
            response.setList(list);
            response.setMessage("Success get all users");
            response.setErrorCode(200);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setMessage("Error when get all users:" + e);
            response.setErrorCode(500);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = ApiPath.USER_LOGIN)
    public ResponseEntity<UserResponseDTO> signIn(@RequestBody UserDTO userDTO) {
        UserResponseDTO response = new UserResponseDTO();
        try {
            String token = service.login(userDTO);
            if (token != null) {
                response.setAccessToken(token);
                response.setMessage("Successful sign in");
                response.setErrorCode(200);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            response.setMessage("UserName or password incorrect!!");
            response.setErrorCode(400);
            return new ResponseEntity<>(response, HttpStatus.NOT_IMPLEMENTED);
        } catch (Exception e) {
            response.setMessage("Error when signIn:" + e);
            response.setErrorCode(500);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping(value = ApiPath.USER_REGISTER)
    public  ResponseEntity<UserResponseDTO> register(@RequestBody UserDTO userDTO) {
        UserResponseDTO response = new UserResponseDTO();
        try {
            UserDTO result = service.create(userDTO);
            if (result != null) {
                response.setMessage("Register Successful ");
                response.setErrorCode(200);
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            response.setMessage("Email already exist !");
            response.setErrorCode(400);
            return new ResponseEntity<>(response, HttpStatus.NOT_IMPLEMENTED);
        } catch (Exception e) {
            response.setMessage("Error when register:" + e);
            response.setErrorCode(500);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // end
}
