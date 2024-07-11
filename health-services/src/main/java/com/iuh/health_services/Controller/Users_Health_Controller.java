package com.iuh.health_services.Controller;

import com.iuh.health_services.Dtos.Health_Redis;
import com.iuh.health_services.Dtos.Request.Healths_Request;
import com.iuh.health_services.Dtos.Respone.Health_Status_Feign;
import com.iuh.health_services.IServices.Impl_Users_Health_Services;
import com.iuh.health_services.Redis.Service_Redis.Health_Service_Redis;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Users_Health_Controller {
    @Autowired
    private Impl_Users_Health_Services usersHealthServices;
    @Autowired 
    private Health_Service_Redis healthServiceRedis;
    @PostMapping("/health/saveHealth")
    public ResponseEntity<?> saveHealth(@RequestBody Healths_Request healthsRequest) throws Exception {
        try {


            return  usersHealthServices.save(healthsRequest);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
    @GetMapping("/health/with-users/{userName}")
    public List<Health_Status_Feign> findAllHealthUsers(@PathVariable("userName") String userName) {
        try {
             return usersHealthServices.findAllHealthWithUsers(userName);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    @GetMapping("/health/{id}")
    public ResponseEntity<?> findOne(@PathVariable("id") String id_reids) {
        try {
            System.out.println(usersHealthServices.findOne(id_reids));
            return ResponseEntity.ok(usersHealthServices.findOne(id_reids));
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    @GetMapping("/health/all")
    public ResponseEntity<?> findAll() {
        try {

            return ResponseEntity.ok(usersHealthServices.findAll());
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    @DeleteMapping("/health/delete/{id}")
    public ResponseEntity<?> deleteOne(@PathVariable("id") String id) {
        try {
            if (usersHealthServices.delete(id)) {
                return ResponseEntity.ok("Delete success");
            } else {
                return ResponseEntity.ok("Delete fail");
            }

        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    @PutMapping("/health/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id, @RequestBody Health_Redis healthRedis) {
        try {

            if (usersHealthServices.update(id, healthRedis)) {
                return ResponseEntity.ok("Update success");
            } else {
                return ResponseEntity.ok("Update fail");
            }

        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
