package com.easy.tour.service.Impl;

import com.easy.tour.Enum.RoleName;
import com.easy.tour.dto.UserDTO;
import com.easy.tour.entity.User.User;
import com.easy.tour.mapper.UserMapper;
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
    UserMapper mapper;

    @Autowired
    PasswordEncoder passwordEncoder;

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
            user.setUserRole(userUtils.setRoleForUser(userDTO.getUserRole()));

            // Send password by email
            sendEmailService.welcomeUserEmail(userDTO.getEmail(), userDTO.getLastName(), userDTO.getFirstName(), randomPassword);
            return mapper.convertEntityToDTO(userRepository.saveAndFlush(user));
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public UserDTO forgotPassword(UserDTO userDTO) {
           if(userRepository.existsByEmail(userDTO.getEmail())) {
               User user = userRepository.findByEmail(userDTO.getEmail()).get();
               String newPassword = userUtils.generatePassword();

               user.setPassword(passwordEncoder.encode(newPassword));
               // Send password by email
               sendEmailService.forgotPasswordEmail(user.getEmail(), user.getLastName(), user.getFirstName(), newPassword);
               return mapper.convertEntityToDTO(userRepository.save(user));
           }

           log.trace("Email does not exist: {}", userDTO.getEmail());
           return null;
    }

    @Override
    public UserDTO getByUUID(String uuid) {
       UserDTO userDTO = new UserDTO();
       User user = userRepository.findByUuid(uuid).get();

       userDTO.setLastName(user.getLastName());
       userDTO.setFirstName(user.getFirstName());
       userDTO.setUuid(user.getUuid());
       userDTO.setEmail(user.getEmail());
       userDTO.setPhoneNumber(user.getPhoneNumber());
       userDTO.setGender(user.getGender());
       userDTO.setAvatarImg(user.getAvatarImg());
       userDTO.setUserRole(userUtils.getRoleForUser(user.getUserRole()));

       return userDTO;
    }

    @Override
    public boolean updateInfo(UserDTO userDTO) {
        try {
            User user = userRepository.findByUuid(userDTO.getUuid()).get();

            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setPhoneNumber(userDTO.getPhoneNumber());
            user.setGender(userDTO.getGender());
            user.setAvatarImg(userDTO.getAvatarImg());
            userRepository.saveAndFlush(user);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean changePassword(UserDTO userDTO) {
        try {
            User user = userRepository.findByUuid(userDTO.getUuid()).get();
            if(passwordEncoder.matches(userDTO.getPassword(), user.getPassword())) {
                if(userDTO.getNewPassword().equals(userDTO.getConfirmNewPassword())) {
                    user.setPassword(passwordEncoder.encode(userDTO.getNewPassword()));
                    userRepository.saveAndFlush(user);
                    return true;
                }
            }
            return false;
        }  catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(String uuid) {
        User user = userRepository.findByUuid(uuid).get();
        if (user != null) {
            userRepository.delete(user);
            return true;
        }
        return false;
    }

}
