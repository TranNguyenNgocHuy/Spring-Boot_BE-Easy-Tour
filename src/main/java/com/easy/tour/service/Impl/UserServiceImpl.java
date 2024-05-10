package com.easy.tour.service.Impl;

import com.easy.tour.Enum.RoleName;
import com.easy.tour.dto.TourDTO;
import com.easy.tour.dto.UserDTO;
import com.easy.tour.entity.Tour.Tour;
import com.easy.tour.entity.User.Role;
import com.easy.tour.entity.User.User;
import com.easy.tour.mapper.AbstractMapper;
import com.easy.tour.mapper.UserMapper;
import com.easy.tour.repository.RoleRepository;
import com.easy.tour.repository.UserRepository;
import com.easy.tour.securtiy.jwt.JwtService;
import com.easy.tour.service.UserService;
import com.easy.tour.utils.AutoSendEmailService;
import com.easy.tour.utils.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl extends AbstractBaseServiceImpl<UserDTO>
        implements UserService {
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

    @Autowired
    UserUtils userUtils;

    @Autowired
    AutoSendEmailService sendEmailService;


    @Override
    public void setRepository() {
        AbstractBaseServiceImpl.setRepository(userRepository);
    }

    public UserServiceImpl() {
        super.setMapper(new UserMapper());
    }


    // For Admin create
    @Override
    public UserDTO createUser(UserDTO userDTO) {
        try {
            if (userRepository.existsByEmail(userDTO.getEmail())) {
                log.info("Email already exist: {}", userDTO.getEmail());
                return null;
            }

            String randomPassword = userUtils.generatePassword();

            User user = new User();
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setPhoneNumber(userDTO.getPhoneNumber());
            user.setGender(userDTO.getGender());
            user.setEmail(userDTO.getEmail());
            user.setPassword(passwordEncoder.encode(randomPassword));
            // Set Role
            for (String roleName : userDTO.getRoles()) {
                if (roleName.equals("ADMIN")) {
                    Role role = roleRepository.findByRoleName(RoleName.ADMIN);
                    user.getRoles().add(role);
                }
                if (roleName.equals("MANAGER")) {
                    Role role = roleRepository.findByRoleName(RoleName.MANAGER);
                    user.getRoles().add(role);
                }
                if (roleName.equals("USER")) {
                    Role role = roleRepository.findByRoleName(RoleName.USER);
                    user.getRoles().add(role);
                }
            }
            System.out.println(user.getPassword());
            // Send password by email
            sendEmailService.sendEmail(userDTO.getEmail(), userDTO.getLastName(), userDTO.getFirstName(), randomPassword);
            return mapper.convertEntityToDTO(userRepository.saveAndFlush(user));
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    // For Client
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

            // Set Role
            for (String roleName : userDTO.getRoles()) {
                if (roleName.equals("ADMIN")) {
                    Role role = roleRepository.findByRoleName(RoleName.ADMIN);
                    user.getRoles().add(role);
                }
                if (roleName.equals("MANAGER")) {
                    Role role = roleRepository.findByRoleName(RoleName.MANAGER);
                    user.getRoles().add(role);
                }
                if (roleName.equals("USER")) {
                    Role role = roleRepository.findByRoleName(RoleName.USER);
                    user.getRoles().add(role);
                }
            }

            return mapper.convertEntityToDTO(userRepository.save(user));
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


    public String login(UserDTO userDTO) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userDTO.getEmail(),
                            userDTO.getPassword())
            );
            User user = userRepository.findByEmail(userDTO.getEmail()).orElseThrow();
            String token = jwtService.generateToken(user);
            return token;
        } catch (AuthenticationException ex) {
            return null;
        }
    }

    @Override
    public UserDTO findByEmail(UserDTO userDTO) {
        return null;
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
