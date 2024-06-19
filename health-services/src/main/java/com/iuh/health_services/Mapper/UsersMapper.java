package com.iuh.health_services.Mapper;

import com.iuh.health_services.Dtos.UserDto;
import com.iuh.health_services.Models.Users;
import org.springframework.stereotype.Service;

@Service
public class UsersMapper {
    public Users toEntity(UserDto usersRequest) {
        return Users.builder()
                .id(usersRequest.getId())
                .fullName(usersRequest.getFullName())
                .age(usersRequest.getAge())
                .email(usersRequest.getEmail())
                .userName(usersRequest.getUserName())
                .build();
    }
    public UserDto toUserDto(Users users) {
        return UserDto.builder()
                .fullName(users.getFullName())
                .age(users.getAge())
                .email(users.getEmail())
                .userName(users.getUserName())
                .build();
    }
}
