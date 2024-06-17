package com.easy.tour.controller;

import com.easy.tour.consts.ApiPath;
import com.easy.tour.dto.UserDTO;
import com.easy.tour.response.UserResponseDTO;
import com.easy.tour.service.AuthService;
import com.easy.tour.service.Impl.AuthServiceImpl;
import com.easy.tour.service.Impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class AuthController {

    @Autowired
    AuthServiceImpl authService;

    @PostMapping(value = ApiPath.USER_LOGIN)
    public ResponseEntity<UserResponseDTO> signIn(@RequestBody UserDTO userDTO) {
        UserResponseDTO response = new UserResponseDTO();
        try {
            UserDTO result = authService.signIn(userDTO);
            if (result == null) {
                response.setMessage("UserName or password incorrect!!");
                response.setErrorCode(403);
                return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
            }
            response.setData(result);
            response.setAccessToken(result.getAccessToken());
            response.setMessage("Successful sign in");
            response.setErrorCode(200);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            response.setMessage("Server error when sign In:" + ex);
            response.setErrorCode(500);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = ApiPath.USER_REGISTER)
    public ResponseEntity<UserResponseDTO> register(@RequestBody UserDTO userDTO) {
        UserResponseDTO response = new UserResponseDTO();
        try {
            UserDTO result = authService.register(userDTO);
            if (result == null) {
                response.setMessage("Email already exist !");
                response.setErrorCode(401);
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            response.setMessage("Register Successful ");
            response.setErrorCode(200);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setMessage("Server error register:" + e);
            response.setErrorCode(500);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
