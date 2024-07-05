package com.iuh.users_healths.Mappers;

import com.iuh.users_healths.Dtos.Reponse.*;
import com.iuh.users_healths.Dtos.Resquest.UsersRequest;
import com.iuh.users_healths.Models.Users;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersMappers {
    public Users toEntity(UsersRequest usersRequest) {
        return Users.builder()
                .fullName(usersRequest.getFullName())
                .age(usersRequest.getAge())
                .email(usersRequest.getEmail())
                .userName(usersRequest.getUserName())
                .sex(usersRequest.isSex())
                .build();
    }

    public UserDto toUserDto(Users users) {
        return UserDto.builder()
                .id(users.getId())
                .fullName(users.getFullName())
                .age(users.getAge())
                .email(users.getEmail())
                .userName(users.getUserName())
                .sex(users.isSex())
                .build();
    }

    public UserDto toUserDtos(Users users) {
        return UserDto.builder()
                .id(users.getId())
                .fullName(users.getFullName())
                .age(users.getAge())
                .email(users.getEmail())
                .userName(users.getUserName())
                .sex(users.isSex())
                .build();
    }

    public Users_Health_Feign toUsersHealthFeign(Users users, List<Health_Status_Feign> health_status) {
        return Users_Health_Feign.builder()
                .fullName(users.getFullName())
                .age(users.getAge())
                .email(users.getEmail())
                .userName(users.getUserName())
                .sex(users.isSex())
                .lsHealth(health_status)
                .build();

    }



}
