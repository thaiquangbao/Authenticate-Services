package com.iuh.users_healths.Mappers;


import com.iuh.users_healths.Dtos.HealthDto;
import com.iuh.users_healths.Dtos.Reponse.Health_Status;
import com.iuh.users_healths.Dtos.Reponse.UserDto;
import com.iuh.users_healths.Dtos.Reponse.Users_Healths_Response;
import com.iuh.users_healths.Dtos.Resquest.Users_Healths_Request;
import com.iuh.users_healths.Models.Healths;
import com.iuh.users_healths.Models.Users;
import com.iuh.users_healths.Models.Users_Health;
import org.springframework.stereotype.Service;

@Service
public class Users_Health_Mapper {
    public Users_Health toUsersHealthEntity(Health_Status users_healths_request, UserDto userDto, HealthDto healthDto) {
        Users users = Users.builder()
                .id(userDto.getId())
                .fullName(userDto.getFullName())
                .age(userDto.getAge())
                .email(userDto.getEmail())
                .userName(userDto.getUserName())
                .sex(userDto.isSex())
                .build();
        Healths healths = Healths.builder()
                .id(healthDto.getId())
                .heartbeat(healthDto.getHeartbeat())
                .bloodPressure(healthDto.getBloodPressure())
                .build();
        return Users_Health.builder()
                .health_condition(users_healths_request.getHealth_condition())
                .status(users_healths_request.getStatus())
                .message_suggest(users_healths_request.getMessage_suggest())
                .created_at(users_healths_request.getCreated_at())
                .users(users)
                .healths(healths)
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
        Healths healths = Healths.builder()
                .heartbeat(users_health.getHealths().getHeartbeat())
                .bloodPressure(users_health.getHealths().getBloodPressure())
                .build();
        return Users_Healths_Response.builder()
                .health_condition(users_health.getHealth_condition())
                .message_suggest(users_health.getMessage_suggest())
                .status(users_health.getStatus())
                .usersDto(userDto)
                .created_at(users_health.getCreated_at())
                .healths(healths)
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
