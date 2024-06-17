package com.easy.tour.service;

import com.easy.tour.dto.UserDTO;

public interface AuthService {
    UserDTO register(UserDTO userDTO);
    UserDTO signIn(UserDTO userDTO);
}
