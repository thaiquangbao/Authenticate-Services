package com.iuh.health_services.Mapper;

import com.iuh.health_services.Dtos.HealthDto;
import com.iuh.health_services.Dtos.Health_Redis;
import com.iuh.health_services.Dtos.Request.Healths_Request;
import com.iuh.health_services.Dtos.Respone.Health_Response;
import com.iuh.health_services.Dtos.Respone.Health_Status_Feign;
import com.iuh.health_services.Models.Healths;
import org.springframework.stereotype.Service;

@Service
public class HealthsMapper {
    public Healths toEntity(Healths_Request healthsRequest) {
        return Healths.builder()
                .heartbeat(healthsRequest.getHeartbeat())
                .bloodPressure(healthsRequest.getBloodPressure())
                .userName(healthsRequest.getUserName())
                .id_redis(healthsRequest.getId_redis())
                .build();
    }
    public Health_Response toResponse(Healths healths) {
        return Health_Response.builder()
                .heartbeat(healths.getHeartbeat())
                .bloodPressure(healths.getBloodPressure())
                .userName(healths.getUserName())
                .id_redis(healths.getId_redis())
                .build();
    }
    public HealthDto toDto(Health_Response healthRespone) {
        return HealthDto.builder()
                .heartbeat(healthRespone.getHeartbeat())
                .bloodPressure(healthRespone.getBloodPressure())
                .userName(healthRespone.getUserName())
                .id_redis(healthRespone.getId_redis())
                .build();
    }
    public Health_Status_Feign toHealthStatus(Healths health) {
        return Health_Status_Feign.builder()
                .heartbeat(health.getHeartbeat())
                .bloodPressure(health.getBloodPressure())
                .id_redis(health.getId_redis())
                .build();
    }
    public Health_Redis toHealthRedis(Healths health) {
        return Health_Redis.builder()
                .heartbeat(health.getHeartbeat())
                .bloodPressure(health.getBloodPressure())
                .userName(health.getUserName())
                .id_redis(health.getId_redis())
                .build();
    }
}
