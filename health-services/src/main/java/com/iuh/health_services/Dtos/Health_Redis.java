package com.iuh.health_services.Dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import java.io.Serializable;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Health_Redis implements Serializable {
    private static final long serialVersionUID = 1L;
    private String heartbeat;
    private String bloodPressure;
    private String userName;
    private String id_redis;
}
