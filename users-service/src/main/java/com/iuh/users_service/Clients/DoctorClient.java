package com.iuh.users_service.Clients;

import com.iuh.users_service.Dtos.Reponse.DoctorFeign;
import com.iuh.users_service.Dtos.Request.GenerateToken;
import com.iuh.users_service.Dtos.Request.Register;
import com.iuh.users_service.Dtos.Request.RegisterDoctor;
import com.iuh.users_service.Dtos.Request.UsersRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "doctor-service", url = "${application.config.doctor-service-url}")
public interface DoctorClient {
    @PostMapping("/doctors/save")
    UsersRequest save(@RequestBody RegisterDoctor users_healths_request);
    @GetMapping("/doctors/getById/{id}")
    DoctorFeign getById(@PathVariable("id") Long id);
}
