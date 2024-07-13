package com.iuh.users_healths.Controllers;

import com.iuh.users_healths.Clients.UsersClient;
import com.iuh.users_healths.Dtos.Reponse.UserDto;
import com.iuh.users_healths.Dtos.Resquest.UsersRequest;
import com.iuh.users_healths.IServices.IUsersHealthServices;
import com.iuh.users_healths.Service.UsersHealthServices;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
public class UsersHealthsControllers {
    @Autowired
    private IUsersHealthServices usersHealthServices;
    @Autowired
    private UsersClient usersClient;
    private static final Logger log = LoggerFactory.getLogger(UsersHealthServices.class);
    @PostMapping("/users-health/save")
    public UserDto save(@RequestBody UsersRequest users_healths_request) throws Exception {
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
    public ResponseEntity<?> findAllHealthOfUser(@PathVariable("userName") String userName) throws ExecutionException, InterruptedException {
        return  usersHealthServices.findAllHealthOfUser(userName).get();
    }

    @GetMapping("/users-health/users/{userName}")
    public ResponseEntity<?> getUsers(@PathVariable("userName") String userName) throws Exception {
        try {
            System.out.println(usersClient.findOneUsers(userName));
            return ResponseEntity.ok(usersClient.findOneUsers(userName));
        } catch (Exception e) {
            throw new Exception(e);
        }

    }
}
