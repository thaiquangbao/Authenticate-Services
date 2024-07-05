package com.iuh.users_healths.Dtos;

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
    private ObjectId id;
    private String heartbeat;
    private String bloodPressure;
    private String userName;
}
