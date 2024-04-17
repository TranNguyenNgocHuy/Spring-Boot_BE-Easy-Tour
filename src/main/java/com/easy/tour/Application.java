package com.easy.tour;

import com.easy.tour.Enum.RoleName;
import com.easy.tour.dto.UserDTO;
import com.easy.tour.entity.User.Role;
import com.easy.tour.entity.User.User;
import com.easy.tour.repository.RoleRepository;
import com.easy.tour.repository.UserRepository;
import com.easy.tour.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

//        @Bean
//    CommandLineRunner createRole(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
//        return args -> {
//            roleRepository.save(new Role(RoleName.ADMIN));
//            roleRepository.save(new Role(RoleName.MANAGER));
//            roleRepository.save(new Role(RoleName.USER));
//
//            Role role = roleRepository.findByRoleName(RoleName.ADMIN);
//
//            User adminUser = new User();
//            adminUser.setUserId(1L);
//            adminUser.setUuid(UUID.randomUUID().toString());
//            adminUser.setLastName("Admin");
//            adminUser.setFirstName("Admin");
//            adminUser.setEmail("admin@admin.com");
//            adminUser.setPassword(passwordEncoder.encode("admin"));
//            adminUser.getRoles().add(role);
//
//            userRepository.save(adminUser);
//        };
//        }

}
