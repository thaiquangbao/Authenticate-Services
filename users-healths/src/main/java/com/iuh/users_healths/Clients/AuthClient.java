package com.iuh.users_healths.Clients;

import com.iuh.users_healths.Dtos.Reponse.ProfileUsers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


//@FeignClient(name = "users-service", url = "application.config.users-url")
public interface AuthClient {
//    @GetMapping("/auth/profile/{userName}")
//    public ProfileUsers getUserByUserName(String userName);
}
