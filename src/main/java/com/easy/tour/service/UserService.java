package com.easy.tour.service;

import com.easy.tour.dto.UserDTO;

public interface UserService extends BaseService<UserDTO> {
    public String login(UserDTO userDTO);

    public UserDTO create(UserDTO userDto);

    boolean update(UserDTO dto);


}
