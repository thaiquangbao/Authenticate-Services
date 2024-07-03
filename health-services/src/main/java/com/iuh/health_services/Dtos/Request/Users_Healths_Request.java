package com.iuh.health_services.Dtos.Request;

import com.iuh.health_services.Dtos.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Users_Healths_Request {
    private String health_condition;
    private String status;
    private String userName;
    private String created_at;
    private String id_health;
}
