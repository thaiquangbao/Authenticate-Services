package com.iuh.health_services.Dtos.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Healths_Request {
    private String heartbeat;
    private String bloodPressure;
    private String userName;
    private String id_redis;}
