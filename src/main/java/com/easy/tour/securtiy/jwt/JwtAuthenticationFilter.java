package com.easy.tour.securtiy.jwt;

import com.easy.tour.securtiy.CustomUserDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Get Token from request header
        String token = getTokenFromRequest(request);

        // Validate Token
        if(StringUtils.hasText(token) && jwtService.validateToken(token)) {

            // Extract userName (Project use UserName = Email)
            String email = jwtService.extractUserName(token);

            // Load userName from DB
            UserDetails userDetails = userDetailsService.loadUserByUsername(email);


            // If User not null. It's mean user is Successful Authentication
            if(userDetails != null) {
                // Tạo mới đối tượng UsernamePasswordAuthenticationToken
                // đại diện cho user đã đc xác thực
                // nhận 3 đối số:
                //            1 Username
                //            2 Password(set = null vì đã xác thực được User r)
                //            3 Authorities (phân quyền)
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails.getUsername(), null, userDetails.getAuthorities());
                log.info("authenticated user with email :{}", email);

                // Đặt userAuthentication vào HolderContext
                // để thông báo User đã xác thực
                // và được xóa đi khi request kết thúc
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest (HttpServletRequest request) {
        final String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7,bearerToken.length());
        }
        return null;
    }

}
