package com.easy.tour.service;

import com.easy.tour.dto.UserDTO;

import java.util.List;

public interface UserService  {
    UserDTO register(UserDTO userDTO);

    UserDTO createUser(UserDTO userDTO);

    String login(UserDTO userDTO);

    UserDTO findByEmail(UserDTO userDTO);
}
