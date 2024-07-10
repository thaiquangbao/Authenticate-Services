package com.iuh.users_healths.Dtos.Reponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Health_Status_Feign {
    private String heartbeat;
    private String bloodPressure;
    private String id_redis;
}
