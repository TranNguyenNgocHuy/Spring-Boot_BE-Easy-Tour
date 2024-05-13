package com.easy.tour.controller;

import com.easy.tour.consts.ApiPath;
import com.easy.tour.dto.UserDTO;
import com.easy.tour.response.UserResponseDTO;
import com.easy.tour.service.Impl.UserServiceImpl;
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
    UserServiceImpl service;

    @GetMapping(value = ApiPath.USER_GET_ALL)
    public ResponseEntity<?> getAllUsers() {
        UserResponseDTO response = new UserResponseDTO();
        try {
            List<UserDTO> list = service.getAll();
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

    @GetMapping (value = ApiPath.USER_GET_UUID)
    public  ResponseEntity<?> getUserByUUID(@PathVariable("uuid") String uuid) {
        UserResponseDTO response = new UserResponseDTO();
        UserDTO userDTO = new UserDTO();
        userDTO.setUuid(uuid);
        try {
            UserDTO result = service.getByUUID(uuid);
            response.setData(result);
            response.setMessage("Success get user by uuid");
            response.setErrorCode(200);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            response.setMessage("Error when get user by uuid");
            response.setErrorCode(500);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = ApiPath.USER_LOGIN)
    public ResponseEntity<UserResponseDTO> signIn(@RequestBody UserDTO userDTO) {
        UserResponseDTO response = new UserResponseDTO();
        try {
            UserDTO result = service.signIn(userDTO);
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

    @PutMapping(value = ApiPath.USER_FORGOT_PASSWORD)
    public ResponseEntity<?> forgotPassword(@RequestBody UserDTO userDTO) {
        UserResponseDTO response = new UserResponseDTO();
        try {
            UserDTO result = service.forgotPassword(userDTO);
            if (result == null) {
                response.setMessage("Email " + userDTO.getEmail() + " does not exist!");
                response.setErrorCode(404);
                return  new ResponseEntity<>( response, HttpStatus.NOT_FOUND);
            }
            response.setMessage("Send new password Successfully");
            response.setErrorCode(200);
            return  new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception ex) {
            response.setMessage("Server error when Send new password: " + ex);
            response.setErrorCode(500);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = ApiPath.USER_CREATE)
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserDTO userDTO) {
        UserResponseDTO response = new UserResponseDTO();
        try {
            UserDTO result = service.createUser(userDTO);
            if (result == null) {
                response.setMessage("Email already exist !");
                response.setErrorCode(401);
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            response.setMessage("Register Successful ");
            response.setErrorCode(200);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception ex) {
            response.setMessage("Server error when create user: " + ex);
            response.setErrorCode(500);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = ApiPath.USER_REGISTER)
    public ResponseEntity<UserResponseDTO> register(@RequestBody UserDTO userDTO) {
        UserResponseDTO response = new UserResponseDTO();
        try {
            UserDTO result = service.register(userDTO);
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
    // end
}
