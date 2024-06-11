package com.iuh.users_service.Mapper;

import com.iuh.users_service.Dtos.Reponse.Authenticated;
import com.iuh.users_service.Dtos.Reponse.ProfileUsers;
import com.iuh.users_service.Dtos.Request.Register;
import com.iuh.users_service.Models.Users_Models;
import org.springframework.stereotype.Service;

@Service
public class Users_Mapper {
    public Users_Models toUsersEntity(Register register) {
        return Users_Models.builder()
                .fullName(register.getFullName())
                .age(register.getAge())
                .passWord(register.getPassWord())
                .email(register.getEmail())
                .sex(register.isSex())
                .role(register.getRole())
                .userName(register.getUserName())
                .build();
    }
    public ProfileUsers toProfileUsers(Users_Models users_models) {
        return ProfileUsers.builder()
                .id(users_models.getId())
                .fullName(users_models.getFullName())
                .passWord(users_models.getPassWord())
                .age(users_models.getAge())
                .email(users_models.getEmail())
                .userName(users_models.getUserName())
                .sex(users_models.isSex())
                .role(users_models.getRole())
                .build();
    }
    public Authenticated toAuthenticated(Users_Models users_models) {
        return Authenticated.builder()
                .id(users_models.getId())
                .fullName(users_models.getFullName())
                .passWord(users_models.getPassWord())
                .age(users_models.getAge())
                .email(users_models.getEmail())
                .userName(users_models.getUserName())
                .sex(users_models.isSex())
                .role(users_models.getRole())
                .build();
    }
}
