package com.easy.tour;

import com.easy.tour.Enum.RoleName;
import com.easy.tour.entity.User.User;
import com.easy.tour.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

@SpringBootApplication
@EnableJpaAuditing
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner createRole(UserRepository userRepository,
                                 PasswordEncoder passwordEncoder) {
        return args -> {
            if (!userRepository.existsByEmail("tran.huy211014@gmail.com")) {
                User adminUser = new User();
                adminUser.setUserId(1L);
                adminUser.setUuid(UUID.randomUUID().toString());
                adminUser.setLastName("Admin");
                adminUser.setFirstName("Admin");
                adminUser.setEmail("tran.huy211014@gmail.com");
                adminUser.setPhoneNumber("0123456789");
                adminUser.setGender(false);
                adminUser.setPassword(passwordEncoder.encode("123456"));
                adminUser.setUserRole(RoleName.ADMIN.getValue());
                userRepository.save(adminUser);
            }
        };
    }

}
