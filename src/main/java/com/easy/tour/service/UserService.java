package com.easy.tour.service;

import com.easy.tour.dto.UserDTO;

import java.util.List;

public interface UserService  {
    UserDTO register(UserDTO userDTO);

    UserDTO createUser(UserDTO userDTO);

    UserDTO signIn(UserDTO userDTO);

    UserDTO forgotPassword(UserDTO userDTO);

    UserDTO getByUUID(String uuid);
}
