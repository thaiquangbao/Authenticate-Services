package com.iuh.users_healths.Controllers;

import com.iuh.users_healths.Dtos.Resquest.UsersRequest;
import com.iuh.users_healths.IServices.IUsersHealthServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
