package com.iuh.health_services.Dtos.Respone;

import com.iuh.health_services.Dtos.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Users_Healths_Response {
    private String health_condition;
    private String status;
    private UserDto usersDto;
}
