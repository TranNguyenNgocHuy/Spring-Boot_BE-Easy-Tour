package com.easy.tour.configure;

import com.easy.tour.securtiy.UserDetailsServiceImpl;
import com.easy.tour.securtiy.jwt.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
//@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return  http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        req -> req.requestMatchers(
                                        "/api/v1/login",
                                        "/api/v1/user/register",
                                        "/api/v1/forgot-password"
                                       )
                                .permitAll()
                                .anyRequest()
                                .permitAll()
                //.authenticated()
                )
                //.userDetailsService(userDetailsServiceImpl)
                //.sessionManagement(session -> session
                //.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                //)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class).build();
    }

    //    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("http://example.com")); // Các domain được phép truy cập
//        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE")); // Các phương thức được phép
//        configuration.setAllowedHeaders(Arrays.asList("*")); // Các header được phép
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
