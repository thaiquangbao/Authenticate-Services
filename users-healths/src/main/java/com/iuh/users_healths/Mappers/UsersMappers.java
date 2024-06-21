package com.iuh.users_healths.Mappers;

import com.iuh.users_healths.Dtos.Reponse.Health_Status;
import com.iuh.users_healths.Dtos.Reponse.ProfileUsers;
import com.iuh.users_healths.Dtos.Reponse.UserDto;
import com.iuh.users_healths.Dtos.Reponse.Users_Health_Reponse;
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

    public UsersRequest toUserDto(Users users) {
        return UsersRequest.builder()
                .fullName(users.getFullName())
                .age(users.getAge())
                .email(users.getEmail())
                .userName(users.getUserName())
                .sex(users.isSex())
                .build();
    }

    public UserDto toUserDtos(Users users) {
        return UserDto.builder()
                .fullName(users.getFullName())
                .age(users.getAge())
                .email(users.getEmail())
                .userName(users.getUserName())
                .sex(users.isSex())
                .build();
    }

    public Users_Health_Reponse toUsersHealthReponse(Users users, List<Health_Status> health_status) {
        return Users_Health_Reponse.builder()
                .fullName(users.getFullName())
                .age(users.getAge())
                .email(users.getEmail())
                .userName(users.getUserName())
                .sex(users.isSex())
                .health_status(health_status)
                .build();

    }
}
