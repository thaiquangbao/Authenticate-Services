package com.iuh.health_services.Controller;

import com.iuh.health_services.Dtos.Request.Users_Healths_Request;
import com.iuh.health_services.Dtos.Respone.Users_Healths_Response;
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
    public ResponseEntity<?> saveHealth(@RequestBody Users_Healths_Request users_healths_request) throws Exception {
        try {
            System.out.println(users_healths_request);
            return usersHealthServices.save(users_healths_request);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
//    @GetMapping("/health/with-users/{health-id}")
//    public ResponseEntity<?> findAllUsers(@PathVariable("health-id") String health_id) {
//        try {
//            Users_Healths_Response rs = usersHealthServices.findAllHealthWithUsers(health_id);
//            return ResponseEntity.ok(rs);
//        }
//        catch (Exception e) {
//            return ResponseEntity.badRequest().body("Error when get all users health!!!");
//        }
//
//    }
}