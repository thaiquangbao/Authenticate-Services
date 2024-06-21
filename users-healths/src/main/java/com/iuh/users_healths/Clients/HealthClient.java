package com.iuh.users_healths.Clients;

import com.iuh.users_healths.Dtos.Reponse.Health_Status;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@FeignClient(name = "health-service", url = "${application.config.healths-url}")
public interface HealthClient {
    @GetMapping("/health/with-users/{userName}")
    public List<Health_Status> findAllUsers(@PathVariable("userName") String health_id);
}
