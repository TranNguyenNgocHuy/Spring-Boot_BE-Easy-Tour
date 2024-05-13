package com.easy.tour;

import com.easy.tour.Enum.RoleName;
import com.easy.tour.dto.UserDTO;
import com.easy.tour.entity.User.Role;
import com.easy.tour.entity.User.User;
import com.easy.tour.repository.RoleRepository;
import com.easy.tour.repository.UserRepository;
import com.easy.tour.service.UserService;
import com.easy.tour.utils.AutoSendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@SpringBootApplication
@EnableJpaAuditing
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner createRole(RoleRepository roleRepository,
                                 UserRepository userRepository,
                                 PasswordEncoder passwordEncoder) {
        return args -> {
            Role r1 = new Role(RoleName.ADMIN);
            Role r2 = new Role(RoleName.MANAGER);
            Role r3 = new Role(RoleName.USER);

            if (!roleRepository.existsByRoleName(RoleName.ADMIN)) {
                roleRepository.save(new Role(RoleName.ADMIN));
            }

            if (!roleRepository.existsByRoleName(RoleName.MANAGER)) {
                roleRepository.save(new Role(RoleName.MANAGER));
            }

            if (!roleRepository.existsByRoleName(RoleName.USER)) {
                roleRepository.save(new Role(RoleName.USER));
            }

            if (!userRepository.existsByEmail("admin@admin.com")) {
                User adminUser = new User();
                adminUser.setUserId(1L);
                adminUser.setUuid(UUID.randomUUID().toString());
                adminUser.setLastName("Admin");
                adminUser.setFirstName("Admin");
                adminUser.setEmail("admin@admin.com");
                adminUser.setGender(false);
                adminUser.setPassword(passwordEncoder.encode("123456"));
                adminUser.getRoles().add(roleRepository.findByRoleName(RoleName.ADMIN));
                userRepository.save(adminUser);
            }
        };
    }


//    @Autowired
//    AutoSendEmailService service;
//
//    @Bean
//    public CommandLineRunner sendEmail() {
//        return args -> {
//            String email = "tran.huy211014@gmail.com";
//            String lastName = "Trần";
//            String firstName = "Huy";
//            String password = "123456";
//
//            service.sendEmail(email, lastName, firstName, password);
//        };
//    }
}
