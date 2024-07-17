package com.iuh.users_service.Controller;

import com.iuh.users_service.Dtos.Reponse.Authenticated;
import com.iuh.users_service.Dtos.Reponse.ProfileUsers;
import com.iuh.users_service.Dtos.Reponse.Token;
import com.iuh.users_service.Dtos.Request.GenerateToken;
import com.iuh.users_service.Dtos.Request.LoginDto;
import com.iuh.users_service.Dtos.Request.Register;
import com.iuh.users_service.Dtos.Request.UpdateUsers;
import com.iuh.users_service.IServices.IUsers_Services;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Authenticate_Controllers {
    @Autowired
    private IUsers_Services usersServices;
    HttpServletResponse response;
    @PostMapping("/auth/signup")
    public ResponseEntity<?> signupUsers(@RequestBody Register users) throws Exception {
        try {
            return usersServices.signupUsers(users);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
    @GetMapping("/auth/all")
    public List<ProfileUsers> getAllUsers() throws Exception {
        try {
//            System.out.println(response.getHeader("accessToken"));
//            System.out.println(response.getHeader("refreshToken"));
            return usersServices.getAllUsers();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
    @PostMapping("/auth/login")
    public ResponseEntity<?> loginAuth(@RequestBody LoginDto loginDtoRequest) throws Exception {
        try {
            System.out.println(loginDtoRequest);
            return usersServices.loginAuth(loginDtoRequest);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
//    @PostMapping("/auth/refreshToken")
//    public ResponseEntity<?> getCurrentUser(@RequestBody Authenticated authenticated) throws Exception {
//        try {
//
//            return usersServices.refreshToken(authenticated);
//        } catch (Exception e) {
//            throw new Exception(e);
//        }
//    }
    @GetMapping("/auth/profile/{userName}")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getUserByUserName(String userName) throws Exception {
        try {
            return usersServices.getUserByUserName(userName);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
    @PostMapping("/auth/update")
    public ResponseEntity<?> updateUsers(@RequestBody UpdateUsers users) throws Exception {
        try {
            return usersServices.updateUsers(users);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
    @PostMapping("/auth/generateToken")
    public ResponseEntity<?> generateToken(@RequestBody GenerateToken generateToken) throws Exception {
        try {
            return usersServices.generateTokenSignup(generateToken);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
    @PostMapping("/auth/userByToken")
    public ResponseEntity<?> getUserByToken(@RequestHeader Token token) throws Exception {
        try {
            System.out.println(token);
            return usersServices.getUserByToken(token);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
