package com.iuh.health_services.Mapper;

import com.iuh.health_services.Dtos.Request.Users_Healths_Request;
import com.iuh.health_services.Dtos.Respone.Health_Status;
import com.iuh.health_services.Dtos.Respone.Users_Healths_Response;
import com.iuh.health_services.Dtos.UserDto;
import com.iuh.health_services.Models.Users;
import com.iuh.health_services.Models.Users_Health;
import org.springframework.stereotype.Service;

@Service
public class Users_Health_Mapper {
    public Users_Health toUsersHealthEntity(Users_Healths_Request users_healths_request, UserDto userDto) {
        Users users = Users.builder()
                .id(userDto.getId())
                .fullName(userDto.getFullName())
                .age(userDto.getAge())
                .email(userDto.getEmail())
                .userName(userDto.getUserName())
                .sex(userDto.isSex())
                .build();
        return Users_Health.builder()
                .health_condition(users_healths_request.getHealth_condition())
                .status(users_healths_request.getStatus())
                .created_at(users_healths_request.getCreated_at())
                .users(users)
                .build();
    }
    public Users_Healths_Response toUsersHealthResponse(Users_Health users_health) {
        UserDto userDto = UserDto.builder()
                .fullName(users_health.getUsers().getFullName())
                .age(users_health.getUsers().getAge())
                .email(users_health.getUsers().getEmail())
                .userName(users_health.getUsers().getUserName())
                .sex(users_health.getUsers().isSex())
                .build();
        return Users_Healths_Response.builder()
                .health_condition(users_health.getHealth_condition())
                .status(users_health.getStatus())
                .usersDto(userDto)
                .created_at(users_health.getCreated_at())
                .build();
    }
    public Health_Status toHealthStatus(Users_Health users_health) {
        return Health_Status.builder()
                .health_condition(users_health.getHealth_condition())
                .status(users_health.getStatus())
                .created_at(users_health.getCreated_at())
                .build();
    }
}
