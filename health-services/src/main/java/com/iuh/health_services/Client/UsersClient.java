package com.iuh.health_services.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

//@FeignClient(name = "users-service", url = "${application.config.users-url}")
public interface UsersClient {
//    @GetMapping("/auth/profile/{userName}")
}
