package com.iuh.users_healths.Controllers;

import com.iuh.users_healths.Dtos.Reponse.UserDto;
import com.iuh.users_healths.Dtos.Resquest.UsersRequest;
import com.iuh.users_healths.IServices.IUsersHealthServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UsersHealthsControllers {
    @Autowired
    private IUsersHealthServices usersHealthServices;
    @PostMapping("/users-health/save")
    public UsersRequest save(@RequestBody UsersRequest users_healths_request) throws Exception {
        try {
            System.out.println(users_healths_request);
            return usersHealthServices.create(users_healths_request);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
    @GetMapping("/users-health/{userName}")
    public UserDto findOneByUserName(@PathVariable("userName") String userName) throws Exception {
        try {
            return usersHealthServices.findOneByUserName(userName);
        } catch (Exception e) {
            throw new Exception(e);
        }

    }
    @GetMapping("/users-health/with-health/{userName}")
    public ResponseEntity<?> findAllHealthOfUser(@PathVariable("userName") String userName) throws Exception {
        try {
            return usersHealthServices.findAllHealthOfUser(userName);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
