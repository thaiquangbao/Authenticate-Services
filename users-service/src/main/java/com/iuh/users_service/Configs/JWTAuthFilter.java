package com.iuh.users_service.Configs;

import com.iuh.users_service.IServices.IUsers_Services;
import com.iuh.users_service.Services.JWTServices;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.userdetails.UserDetails;
import java.io.IOException;


@Component
public class JWTAuthFilter extends OncePerRequestFilter {
    @Autowired
    @Lazy
    private JWTServices jwtUtils;
    @Autowired
    @Lazy
    private IUsers_Services users_Services;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");
        String jwtToken;
        String userName;
        if (authorizationHeader == null || authorizationHeader.isBlank()) {
            filterChain.doFilter(request, response);
            return;
        }
        jwtToken = authorizationHeader.substring(7);
        userName = jwtUtils.extractUserName(jwtToken);

        Authentication authenticated = SecurityContextHolder.getContext().getAuthentication();
        // Nếu userName người dùng đăng nhập vào còn tồn tại và chưa được xác thực
        if (userName != null && authenticated == null) {
            UserDetails userDetails = users_Services.loadUserByUsername(userName);
            if (jwtUtils.isTokenvalid(jwtToken, userDetails)) {
                // Nếu token hợp lệ, set thông tin cho Security Context và xác thực người dùng
                SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
                // bỏ thông tin người dùng đăng nhập vào SecurityContextHolder thông qua token trong đó có chứa user vừa login
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                        userDetails,null,userDetails.getAuthorities()
                );
                token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                securityContext.setAuthentication(token);
                SecurityContextHolder.setContext(securityContext);
//                System.out.println("user active:" + securityContext);
            }
        }
        filterChain.doFilter(request,response);
    }
}
