package com.easy.tour.securtiy;

import com.easy.tour.Enum.RoleName;
import com.easy.tour.entity.User.User;
import com.easy.tour.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;

@AllArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found !"));

        Set<GrantedAuthority> authorities;
        if (user.getUserRole() == RoleName.USER.getValue()) {
            authorities = Collections.singleton(new SimpleGrantedAuthority(RoleName.ROLE_USER));
        } else if (user.getUserRole() == RoleName.MANAGER.getValue()) {
            authorities = Collections.singleton(new SimpleGrantedAuthority(RoleName.ROLE_MANAGER));
        } else {
            authorities = Collections.singleton(new SimpleGrantedAuthority(RoleName.ROLE_ADMIN));
        }

        System.out.println(authorities);

        return new org.springframework.security.core.userdetails.User(email, user.getPassword(), authorities);
    }
}
