package com.iuh.users_healths.Mappers;

import com.iuh.users_healths.Dtos.Reponse.ProfileUsers;
import com.iuh.users_healths.Dtos.Resquest.UsersRequest;
import com.iuh.users_healths.Models.Users;
import org.springframework.stereotype.Service;

@Service
public class UsersMappers {
    public Users toEntity(UsersRequest usersRequest) {
        return Users.builder()
                .id(usersRequest.getId())
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
    public Users toUserModel(ProfileUsers profileUsers) {
        return Users.builder()
                .id(profileUsers.getId())
                .fullName(profileUsers.getFullName())
                .age(profileUsers.getAge())
                .email(profileUsers.getEmail())
                .userName(profileUsers.getUserName())
                .build();
    }
}
