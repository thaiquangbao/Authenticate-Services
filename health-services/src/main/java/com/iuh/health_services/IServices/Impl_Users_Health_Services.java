package com.iuh.health_services.IServices;

import com.iuh.health_services.Dtos.Health_Redis;
import com.iuh.health_services.Dtos.Request.Healths_Request;
import com.iuh.health_services.Dtos.Respone.Health_Status_Feign;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface Impl_Users_Health_Services {
    public List<Health_Redis> findAll();
//    public Users_Health findByEmail(String email);
    public ResponseEntity<?> save(Healths_Request healthsRequest);
    public boolean delete(String id);
    public boolean update(String id, Health_Redis healthRedis);
    public List<Health_Status_Feign> findAllHealthWithUsers(String userName);
    public Health_Redis findOne(String id_redis);
}
