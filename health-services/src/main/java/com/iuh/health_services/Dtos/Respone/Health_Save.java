package com.iuh.health_services.Dtos.Respone;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Health_Save {
    private ObjectId id;
    private String heartbeat;
    private String bloodPressure;
    private String userName;
}
