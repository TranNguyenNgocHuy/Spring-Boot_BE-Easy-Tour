package com.easy.tour.service;

import com.easy.tour.dto.UserDTO;

import java.util.List;

public interface UserService  {
    public String login(UserDTO userDTO);

    public UserDTO register(UserDTO userDto);
}
