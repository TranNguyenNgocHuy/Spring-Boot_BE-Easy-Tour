package com.easy.tour.mapper;

import com.easy.tour.dto.UserDTO;
import com.easy.tour.entity.User.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapper extends AbstractMapper<User, UserDTO> {
    public UserMapper() {
        super(User.class, UserDTO.class);
    }
}
