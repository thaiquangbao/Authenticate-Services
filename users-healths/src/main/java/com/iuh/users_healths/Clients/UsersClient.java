package com.iuh.users_healths.Clients;

import com.iuh.users_healths.Dtos.Reponse.Health_Status_Feign;
import com.iuh.users_healths.Dtos.Reponse.ProfileUsers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "users-service-health", url = "${application.config.users-service-health-url}")
public interface UsersClient {
    @GetMapping("/users/{userName}")
    public ProfileUsers findOneUsers(@PathVariable("userName") String userName);
}
