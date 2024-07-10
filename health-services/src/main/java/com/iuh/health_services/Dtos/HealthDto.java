package com.iuh.health_services.Dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HealthDto {
    private String heartbeat;
    private String bloodPressure;
    private String userName;
    private String id_redis;
}
