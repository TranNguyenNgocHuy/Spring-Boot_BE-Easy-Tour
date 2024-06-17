package com.easy.tour.service.Impl;

import com.easy.tour.dto.UserDTO;
import com.easy.tour.entity.User.User;
import com.easy.tour.mapper.UserMapper;
import com.easy.tour.repository.UserRepository;
import com.easy.tour.securtiy.jwt.JwtService;
import com.easy.tour.service.AuthService;
import com.easy.tour.utils.UserUtils;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtService jwtService;

    @Autowired
    UserMapper mapper;

    @Autowired
    UserUtils userUtils;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDTO register(UserDTO userDTO) {
        try {
            if (userRepository.existsByEmail(userDTO.getEmail())) {
                log.info("Email already exist: {}", userDTO.getEmail());
                return null;
            }
            User user = new User();
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setEmail(userDTO.getEmail());
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            user.setUserRole(userUtils.setRoleForUser(userDTO.getUserRole()));

            return mapper.convertEntityToDTO(userRepository.save(user));
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public UserDTO signIn(UserDTO userDTO) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword())
        );

        GrantedAuthority grantedAuthority = authentication.getAuthorities().stream().findFirst().orElseThrow(() -> new IllegalArgumentException("Not found Role"));

        String token = jwtService.generateToken(authentication, grantedAuthority);

        userRepository.findByEmail(userDTO.getEmail()).ifPresentOrElse(user -> {
            userDTO.setUuid(user.getUuid());
            userDTO.setPassword(null);
            userDTO.setFirstName(user.getFirstName());
            userDTO.setLastName(user.getLastName());
            userDTO.setAccessToken(token);
        }, () -> {
                throw new IllegalArgumentException("Not found email");
        });
        return userDTO;
    }

}

