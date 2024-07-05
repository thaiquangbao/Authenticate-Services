package com.iuh.health_services.Dtos.Respone;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Health_Status_Feign {
    private String heartbeat;
    private String bloodPressure;
}
