package com.iuh.health_services.Client;

import com.iuh.health_services.Dtos.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "users-healths", url = "${application.config.users-healths-url}")
public interface UsersClient {
    @GetMapping("/users-health/{userName}")
    public UserDto findOneByUserName(@PathVariable("userName") String userName);
}
