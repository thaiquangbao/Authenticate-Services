package com.iuh.health_services.Controller;

import com.iuh.health_services.Dtos.Request.Healths_Request;
import com.iuh.health_services.Dtos.Respone.Health_Status_Feign;
import com.iuh.health_services.IServices.Impl_Users_Health_Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Users_Health_Controller {
    @Autowired
    private Impl_Users_Health_Services usersHealthServices;
    @PostMapping("/health/saveHealth")
    public ResponseEntity<?> saveHealth(@RequestBody Healths_Request healthsRequest) throws Exception {
        try {
            System.out.println(healthsRequest);
            usersHealthServices.save(healthsRequest);
            return ResponseEntity.ok("Sending health suggest success!!!");
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
    @GetMapping("/health/with-users/{userName}")
    public List<Health_Status_Feign> findAllUsers(@PathVariable("userName") String userName) {
        try {
             return usersHealthServices.findAllHealthWithUsers(userName);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
