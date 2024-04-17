package com.easy.tour.securtiy.jwt;

import com.easy.tour.securtiy.UserDetailsServiceImpl;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {


        // lấy Token từ header khi có request đến
        String token = jwtService.getToken(request);

        // Nếu có token và chữ ký trong Token hợp lệ
        if(token != null && jwtService.validateToken(token)) {

            // Trích xuất User name (dự án lấy email làm username)
            String email = jwtService.extractUserName(token);

            // Từ Email lấy User từ DB
            UserDetails userDetails = userDetailsService.loadUserByUsername(email);

            // Nếu User ko null tức là User đã Successful Authentication
            if(userDetails != null) {

                // UsernamePasswordAuthenticationToken đc tạo mới
                // bên dưới đại diện cho user đã đc xác thực
                // nhận 3 đối số:
                //            1 Username
                //            2 Password(set = null vì đã xác thực được User r)
                //            3 Authorities (phân quyền)
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());
                log.info("authenticated user with email :{}", email);

                // Đặt userAuthentication vào HolderContext
                // để thông báo User đã xác thực và sẽ bị xóa
                // khi request kết thúc
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }
}
