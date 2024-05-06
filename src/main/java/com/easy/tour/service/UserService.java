package com.easy.tour.service;

import com.easy.tour.dto.UserDTO;

import java.util.List;

public interface UserService  {
    UserDTO register(UserDTO userDto);

    String login(UserDTO userDTO);
}
