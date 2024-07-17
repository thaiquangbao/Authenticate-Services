package com.iuh.users_service.Configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iuh.users_service.Dtos.Reponse.Authenticated;
import com.iuh.users_service.Dtos.Reponse.Token;
import com.iuh.users_service.Dtos.Request.GenerateToken;
import com.iuh.users_service.Dtos.Request.ReturnToken;
import com.iuh.users_service.Dtos.UserDto;
import com.iuh.users_service.Dtos.UserDtoCheck;
import com.iuh.users_service.IServices.IUsers_Services;
import com.iuh.users_service.Models.Users_Models;
import com.iuh.users_service.Services.JWTServices;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.core.userdetails.UserDetails;
import java.io.IOException;
import java.util.HashMap;


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
        String accessToken = request.getHeader("accessToken");
        String refreshToken = request.getHeader("refreshToken");

        if (accessToken == null || accessToken.isBlank()) {
            filterChain.doFilter(request, response);
            return;
        }

        UserDtoCheck userDetails = new UserDtoCheck();
        boolean checkAccessToken = jwtUtils.isExpiration(accessToken);
        if (checkAccessToken) {
            String userName = jwtUtils.extractUserName(accessToken);
            Authentication authenticated = SecurityContextHolder.getContext().getAuthentication();
            UserDto userGet = users_Services.getUserById(Long.parseLong(userName));
            userDetails.setData(userGet);
            jwtUtils.isTokenvalid(accessToken, userDetails.getData());

            if (userName != null && authenticated == null) {
                authenticateUser(userDetails.getData(), request);
            }
            filterChain.doFilter(request, response);
        } else {
            boolean checkRefreshToken = jwtUtils.isExpiration(refreshToken);
            if (checkRefreshToken) {
                try {
                    String userName = jwtUtils.extractUserName(refreshToken);
                    Authentication authenticated = SecurityContextHolder.getContext().getAuthentication();
                    UserDto userGet = users_Services.getUserById(Long.parseLong(userName));
                    userDetails.setData(userGet);

                    HashMap<String, String> claims = new HashMap<>();
                    claims.put("role", userDetails.getData().getRole());
                    ReturnToken newTokens = jwtUtils.reloadRefreshToken(claims, refreshToken, userDetails);

                    Token token = new Token();
                    token.setAccessToken(newTokens.getAccessToken());
                    token.setRefreshToken(newTokens.getRefreshToken());
                    userDetails.setToken(token);

                    if (userName != null && authenticated == null) {
                        authenticateUser(userDetails.getData(), request);

                    }


                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");

                    // Chuyển đổi đối tượng userDetails sang JSON
                    ObjectMapper objectMapper = new ObjectMapper();
                    String userDetailsJson = objectMapper.writeValueAsString(userDetails);

                    // Ghi JSON vào phản hồi
                    response.getWriter().write(userDetailsJson);

                } catch (Exception ex) {
                    System.out.println(ex);
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write("{\"message\": \"Phiên đăng nhập đã hết hạn \"}");
                }
            } else {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("{\"message\": \"Phiên đăng nhập đã hết hạn \"}");

            }
        }



    }





    private void authenticateUser(UserDetails userDetails, HttpServletRequest request) {
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities()
        );
        System.out.println(token);
        token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        securityContext.setAuthentication(token);
        SecurityContextHolder.setContext(securityContext);
    }


}
