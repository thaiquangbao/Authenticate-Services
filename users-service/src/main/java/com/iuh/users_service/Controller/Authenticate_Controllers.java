package com.iuh.users_service.Controller;


import com.iuh.users_service.Dtos.CheckValidAccount;
import com.iuh.users_service.Dtos.Reponse.Token;
import com.iuh.users_service.Dtos.Request.*;
import com.iuh.users_service.Dtos.UpdateDoctors;
import com.iuh.users_service.IServices.IUsers_Services;
import com.iuh.users_service.Models.Users_Models;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Authenticate_Controllers {
    private static final Logger log = LoggerFactory.getLogger(Authenticate_Controllers.class);
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
    public ResponseEntity<?> getAllUsers(@RequestHeader String accessToken, @RequestHeader String refreshToken) throws Exception {
        try {
//            System.out.println(response.getHeader("accessToken"));
//            System.out.println(response.getHeader("refreshToken"));
            Token token = new Token();
            token.setAccessToken(accessToken);
            token.setRefreshToken(refreshToken);
            return usersServices.getAllUsers(token);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
    @PostMapping("/auth/login")
    public ResponseEntity<?> loginAuth(@RequestBody LoginDto loginDtoRequest) throws Exception {
        try {
            return usersServices.loginAuth(loginDtoRequest);
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
    @GetMapping("/auth/userByToken")
    public ResponseEntity<?> getUserByToken(@RequestHeader String accessToken, @RequestHeader String refreshToken) throws Exception {
        try {
            Token token = new Token();
            token.setAccessToken(accessToken);
            token.setRefreshToken(refreshToken);


            return usersServices.getUserByToken(token);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
    @PostMapping("/auth/create-doctor")
    public ResponseEntity<?> createDoctor(@RequestBody RegisterDoctor users, @RequestHeader String accessToken, @RequestHeader String refreshToken) throws Exception {
        try {
            Token token = new Token();
            token.setAccessToken(accessToken);
            token.setRefreshToken(refreshToken);
            return usersServices.createDoctor(users, token);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
    @GetMapping("/auth/test")
    public ResponseEntity<?> testDoctor(@RequestBody GenerateToken generateToken) throws Exception {
        try {
            return ResponseEntity.ok(usersServices.getDoctorById(generateToken.getId()));
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
    @PutMapping("/auth/updateDoctor/{id}")
    public Users_Models updateDoctor(@RequestBody UpdateDoctors users, @PathVariable Long id) throws Exception {
        try {
            System.out.println(users);
            users.setId(id);
            return usersServices.updateDoctor(users, id);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
    @DeleteMapping("/auth/deleteOneDoctor/{id}")
    public boolean deleteOneDoctor(@PathVariable Long id) throws Exception {
        try {
            return usersServices.deleteOneDoctor(id);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
    @PostMapping("/auth/deleteManyDoctor")
    public boolean deleteOneDoctor(@RequestBody List<Long> ids) throws Exception {
        try {
            return usersServices.deleteManyDoctor(ids);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
    @GetMapping("/auth/getOneUser")
    public ResponseEntity<?> getOneUser(@RequestBody GenerateToken generateToken,  @RequestHeader String accessToken, @RequestHeader String refreshToken) throws Exception {
        try {
            Token token = new Token();
            token.setAccessToken(accessToken);
            token.setRefreshToken(refreshToken);
            return ResponseEntity.ok(usersServices.getOneUser(generateToken.getId(), token));
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Not found");
        }
    }
    @PutMapping("/auth/updateUser/{id}")
    public int updateUser(@RequestBody UserDtoUpdate users, @PathVariable Long id) throws Exception {
        try {
            users.setRole("USER");
            return usersServices.updateOneUser(id, users);
        } catch (Exception e) {
            log.warn(e.getMessage());
            throw new Exception(e);
        }
    }
    @PostMapping("/auth/checkEmailAndPhone/{id}")
    public int checkUser(@RequestBody CheckValidAccount checkValidAccount, @PathVariable Long id) throws Exception {
        try {
            return usersServices.checkEmailAndPhone(checkValidAccount.getPhone(), checkValidAccount.getEmail(), id);
        } catch (Exception e) {
            log.warn(e.getMessage());
            throw new Exception(e);
        }
    }
}
