package com.iuh.health_services.IServices;

import com.iuh.health_services.Dtos.Request.Healths_Request;
import com.iuh.health_services.Dtos.Respone.Health_Status_Feign;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface Impl_Users_Health_Services {
//    public List<Users_Healths_Response> findAll();
//    public Users_Health findByEmail(String email);
    public ResponseEntity<?> save(Healths_Request healthsRequest);
//    public void delete(String id);
//    public Users_Health update(Users_Health users_health);
    public List<Health_Status_Feign> findAllHealthWithUsers(String userName);

}
