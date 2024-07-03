package com.iuh.health_services.Controller;

import com.iuh.health_services.Dtos.Request.Healths_Request;
import com.iuh.health_services.Dtos.Request.Users_Healths_Request;
import com.iuh.health_services.Dtos.Respone.Health_Status;
import com.iuh.health_services.Dtos.Respone.Users_Healths_Response;
import com.iuh.health_services.IServices.Impl_Users_Health_Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@RestController
public class Users_Health_Controller {
    @Autowired
    private Impl_Users_Health_Services usersHealthServices;
    @PostMapping("/health/saveHealth")
    public ResponseEntity<?> saveHealth(@RequestBody Healths_Request healthsRequest) throws Exception {
        try {
            System.out.println(healthsRequest);
            return usersHealthServices.save(healthsRequest);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
    @GetMapping("/health/with-users/{userName}")
    public List<Health_Status> findAllUsers(@PathVariable("userName") String health_id) {
        try {
             return usersHealthServices.findAllHealthWithUsers(health_id);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
