package com.iuh.health_services.Mapper;

import com.iuh.health_services.Dtos.Request.Users_Healths_Request;
import com.iuh.health_services.Dtos.Respone.Users_Healths_Response;
import com.iuh.health_services.Dtos.UserDto;
import com.iuh.health_services.Models.Users;
import com.iuh.health_services.Models.Users_Health;
import org.springframework.stereotype.Service;

@Service
public class Users_Health_Mapper {
    public Users_Health toUsersHealthEntity(Users_Healths_Request users_healths_request) {
        Users users = Users.builder()
                .id(users_healths_request.getUsers().getId())
                .fullName(users_healths_request.getUsers().getFullName())
                .age(users_healths_request.getUsers().getAge())
                .email(users_healths_request.getUsers().getEmail())
                .userName(users_healths_request.getUsers().getUserName())
                .build();
        return Users_Health.builder()
                .health_condition(users_healths_request.getHealth_condition())
                .status(users_healths_request.getStatus())
                .users(users)
                .build();
    }
    public Users_Healths_Response toUsersHealthResponse(Users_Health users_health) {
        UserDto userDto = UserDto.builder()
                .fullName(users_health.getUsers().getFullName())
                .age(users_health.getUsers().getAge())
                .email(users_health.getUsers().getEmail())
                .userName(users_health.getUsers().getUserName())
                .build();
        return Users_Healths_Response.builder()
                .health_condition(users_health.getHealth_condition())
                .status(users_health.getStatus())
                .usersDto(userDto)
                .build();
    }
}
