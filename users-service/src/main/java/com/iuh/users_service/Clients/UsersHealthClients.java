package com.iuh.users_service.Clients;

import com.iuh.users_service.Dtos.Request.UsersRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "users-healths", url = "${application.config.users-healths-url}")
public interface UsersHealthClients {
    @PostMapping("/users-health/save")
    public UsersRequest save(@RequestBody UsersRequest users_healths_request);
}
