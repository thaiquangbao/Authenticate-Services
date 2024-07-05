package com.iuh.users_healths.Dtos.Reponse;

import com.iuh.users_healths.Models.Healths;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Users_Healths_Response {
    private String health_condition;
    private String status;
    private String message_suggest;
    private UserDto usersDto;
    private String created_at;
    private Healths healths;
}
