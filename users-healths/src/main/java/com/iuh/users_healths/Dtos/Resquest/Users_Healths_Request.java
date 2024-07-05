package com.iuh.users_healths.Dtos.Resquest;

import com.iuh.users_healths.Dtos.HealthDto;
import com.iuh.users_healths.Dtos.Reponse.UserDto;
import com.iuh.users_healths.Models.Healths;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Users_Healths_Request {
    private String health_condition;
    private String status;
    private String message_suggest;
    private UserDto usersDto;
    private String created_at;
    private HealthDto healths;
    private String usersName;
}
