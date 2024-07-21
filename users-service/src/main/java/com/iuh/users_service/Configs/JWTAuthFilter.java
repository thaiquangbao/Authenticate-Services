package com.iuh.users_service.Configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iuh.users_service.Dtos.DoctorDtoCheck;
import com.iuh.users_service.Dtos.Reponse.Authenticated;
import com.iuh.users_service.Dtos.Reponse.DoctorAuth;
import com.iuh.users_service.Dtos.Reponse.Token;
import com.iuh.users_service.Dtos.Request.GenerateToken;
import com.iuh.users_service.Dtos.Request.ReturnToken;
import com.iuh.users_service.Dtos.UserDto;
import com.iuh.users_service.Dtos.UserDtoCheck;
import com.iuh.users_service.IServices.IUsers_Services;
import com.iuh.users_service.Mapper.Users_Mapper;
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
    @Autowired
    private Users_Mapper userMapper;;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String accessToken = request.getHeader("accessToken");
        String refreshToken = request.getHeader("refreshToken");

        if (accessToken == null || accessToken.isBlank()) {
            filterChain.doFilter(request, response);
            return;
        }

        UserDtoCheck userDetails = new UserDtoCheck();
        DoctorDtoCheck doctorDetail = new DoctorDtoCheck();
        try {
            jwtUtils.isExpiration(accessToken);
            String userName = jwtUtils.extractUserName(accessToken);
            Authentication authenticated = SecurityContextHolder.getContext().getAuthentication();
            UserDto userGet = users_Services.getUserById(Long.parseLong(userName));
            if (userGet.getRole().matches("DOCTOR")) {
                DoctorAuth doctorGet = users_Services.getDoctorById(Long.parseLong(userName));
                doctorDetail.setData(doctorGet);
                jwtUtils.isTokenvalid(accessToken, doctorDetail.getData());

                if (userName != null && authenticated == null) {
                    authenticateUser(doctorDetail.getData(), request);
                }
            } else {
                userDetails.setData(userGet);
                jwtUtils.isTokenvalid(accessToken, userDetails.getData());

                if (userName != null && authenticated == null) {
                    authenticateUser(userDetails.getData(), request);
                }
            }

            filterChain.doFilter(request, response);
        } catch(Exception ex) {
                try {
                    jwtUtils.isExpiration(refreshToken);
                    String userName = jwtUtils.extractUserName(refreshToken);
                    Authentication authenticated = SecurityContextHolder.getContext().getAuthentication();
                    UserDto userGet = users_Services.getUserById(Long.parseLong(userName));
                    if (userGet.getRole().matches("DOCTOR")) {
                        DoctorAuth doctorGet = users_Services.getDoctorById(Long.parseLong(userName));
                        doctorDetail.setData(doctorGet);
                        HashMap<String, String> claims = new HashMap<>();
                        claims.put("role", doctorDetail.getData().getRole());
                        ReturnToken newTokens = jwtUtils.reloadRefreshTokenDoctor(claims, refreshToken, doctorDetail);
                        Token tokenDoctor = new Token();
                        tokenDoctor.setAccessToken(newTokens.getAccessToken());
                        tokenDoctor.setRefreshToken(newTokens.getRefreshToken());
                        doctorDetail.setToken(tokenDoctor);

                        if (userName != null && authenticated == null) {
                            authenticateUser(doctorDetail.getData(), request);

                        }


//                        response.setContentType("application/json");
//                        response.setCharacterEncoding("UTF-8");
//
//                        // Chuyển đổi đối tượng userDetails sang JSON
//                        ObjectMapper objectMapper = new ObjectMapper();
//                        String doctorDetailsJson = objectMapper.writeValueAsString(userMapper.doctorChecktoDoctorLogin(doctorDetail, tokenDoctor));
//
//                        // Ghi JSON vào phản hồi
//                        response.getWriter().write(doctorDetailsJson);
                        filterChain.doFilter(request, response);

                    } else {
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

//
//                        response.setContentType("application/json");
//                        response.setCharacterEncoding("UTF-8");
//
//                        // Chuyển đổi đối tượng userDetails sang JSON
//                        ObjectMapper objectMapper = new ObjectMapper();
//                        String userDetailsJson = objectMapper.writeValueAsString(userDetails);
//
//                        // Ghi JSON vào phản hồi
//                        response.getWriter().write(userDetailsJson);
                        filterChain.doFilter(request, response);
                    }


                } catch (Exception e) {
                    System.out.println(e);
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.getWriter().write("{\"message\": \"Phiên đăng nhập đã hết hạn !!!\"}");
                }

        }



    }





    private void authenticateUser(UserDetails userDetails, HttpServletRequest request) {
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities()
        );
        token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        securityContext.setAuthentication(token);
        SecurityContextHolder.setContext(securityContext);
    }


}
