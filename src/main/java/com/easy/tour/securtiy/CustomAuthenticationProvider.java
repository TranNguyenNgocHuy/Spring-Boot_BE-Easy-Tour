//package com.easy.tour.securtiy;
//
//import com.easy.tour.Enum.RoleName;
//import com.easy.tour.entity.User.User;
//import com.easy.tour.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import java.util.Collections;
//import java.util.Set;
//
//
//@Slf4j
//@RequiredArgsConstructor
//public class CustomAuthenticationProvider implements AuthenticationProvider {
//
//    private final UserRepository repository;
//    private final PasswordEncoder passwordEncoder;
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String email = authentication.getName();
//        String password = authentication.getCredentials().toString();
//        System.out.println(password);
//
//        User user = repository.findByEmail(email).orElseThrow(() -> new BadCredentialsException("User not found !"));
//
//        if(passwordEncoder.matches(password, user.getPassword())) {
//            return new UsernamePasswordAuthenticationToken(email, password, getGrantedAuthority(user));
//        } else throw new BadCredentialsException("User not found!");
//
//    }
//
//    private Set<GrantedAuthority> getGrantedAuthority(User user) {
//        Set<GrantedAuthority> authorities;
//        if (user.getUserRole() == RoleName.USER.getValue()) {
//            authorities = Collections.singleton(new SimpleGrantedAuthority(RoleName.ROLE_USER));
//        } else if (user.getUserRole() == RoleName.MANAGER.getValue()) {
//            authorities = Collections.singleton(new SimpleGrantedAuthority(RoleName.ROLE_MANAGER));
//        } else {
//            authorities = Collections.singleton(new SimpleGrantedAuthority(RoleName.ROLE_ADMIN));
//        }
//        return authorities;
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
//    }
//}
