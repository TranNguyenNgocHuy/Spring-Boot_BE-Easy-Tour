package com.easy.tour.service;

import com.easy.tour.dto.UserDTO;

import java.util.List;

public interface UserService  {
    UserDTO createUser(UserDTO userDTO);

    UserDTO forgotPassword(UserDTO userDTO);

    UserDTO getByUUID(String uuid);

    boolean updateInfo(UserDTO userDTO);

    boolean changePassword(UserDTO userDTO);

    boolean delete(String uuid);

}
