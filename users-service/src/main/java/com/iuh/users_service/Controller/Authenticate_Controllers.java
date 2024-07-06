package com.iuh.users_service.Controller;

import com.iuh.users_service.Dtos.Reponse.Authenticated;
import com.iuh.users_service.Dtos.Reponse.ProfileUsers;
import com.iuh.users_service.Dtos.Request.LoginDto;
import com.iuh.users_service.Dtos.Request.Register;
import com.iuh.users_service.IServices.IUsers_Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class Authenticate_Controllers {
    @Autowired
    private IUsers_Services usersServices;
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
    @PostMapping("/auth/refreshToken")
    public ResponseEntity<?> getCurrentUser(@RequestBody Authenticated authenticated) throws Exception {
        try {

            return usersServices.refreshToken(authenticated);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
    @GetMapping("/auth/profile/{userName}")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getUserByUserName(String userName) throws Exception {
        try {
            return usersServices.getUserByUserName(userName);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
