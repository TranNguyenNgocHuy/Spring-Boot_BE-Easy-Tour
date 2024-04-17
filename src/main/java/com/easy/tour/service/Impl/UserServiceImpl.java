package com.easy.tour.service.Impl;

import com.easy.tour.Enum.RoleName;
import com.easy.tour.dto.UserDTO;
import com.easy.tour.entity.User.User;
import com.easy.tour.mapper.UserMapper;
import com.easy.tour.repository.RoleRepository;
import com.easy.tour.repository.UserRepository;
import com.easy.tour.securtiy.jwt.JwtService;
import com.easy.tour.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserMapper mapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtService jwtService;


    // Tạo User
    @Override
    public UserDTO create(UserDTO userDto) {
        UserDTO result = new UserDTO();

        try {
            if (userRepository.existsByEmail(userDto.getEmail())) {
                log.trace("Email already exist: {}", userDto.getEmail());
                return null;
            }

            User user = new User();
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setEmail(userDto.getEmail());
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));

            // Set Role
            if (userDto.getRoleName() == 1) {
                user.getRoles().add(roleRepository.findByRoleName(RoleName.ADMIN));
            } else if (userDto.getRoleName() == 2) {
                user.getRoles().add(roleRepository.findByRoleName(RoleName.USER));
            } else if (userDto.getRoleName() == 3) {
                user.getRoles().add(roleRepository.findByRoleName(RoleName.MANAGER));
            }

            result = mapper.convertEntityToDTO(userRepository.save(user));
        } catch (Exception ex) {
            log.error("Error when creating:", ex);
        }
        return result;
    }

    // Xác thực khi User login
    public String login(UserDTO userDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userDTO.getEmail(),
                        userDTO.getPassword()
                )
        );

        User user = userRepository.findByEmail(userDTO.getEmail()).orElseThrow();
        String token = jwtService.generateToken(user);

        return token;
    }

    @Override
    public boolean update(UserDTO dto) {
        try {
            User user = mapper.convertDTOToEntity(dto);
            mapper.convertEntityToDTO(userRepository.saveAndFlush(user));
            return true;
        } catch (Exception ex) {
            log.error("Error when updating:", ex);
            return false;
        }
    }

    @Override
    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();
        return users == null ? new ArrayList<>()
                : users.stream().map(entity -> mapper.convertEntityToDTO(entity))
                                .collect(Collectors.toList());
    }

//    @Override
//    public boolean delete(String uuid) {
//        User user = repository.findByUuid(uuid);
//        if (user != null) {
//            repository.delete(user);
//            return true;
//        }
//        return false;
//    }

//    @Override
//    public UserDTO login(String name, String password) {
//        User user = userRepository.findByUserNameAndPassword(name, password);
//        return user == null ?  null : mapper.convertEntityToDTO(user);
//    }
}
