package com.iuh.users_service.Mapper;

import com.iuh.users_service.Dtos.Reponse.Authenticated;
import com.iuh.users_service.Dtos.Reponse.ProfileUsers;
import com.iuh.users_service.Dtos.Request.Register;
import com.iuh.users_service.Dtos.Request.UsersRequest;
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
                .image(register.getImage())
                .address(register.getAddress())
                .dateOfBirth(register.getDateOfBirth())
                .phone(register.getPhone())
                .build();
    }

    public ProfileUsers toProfileUsers(Users_Models users_models) {
        return ProfileUsers.builder()
                .id(users_models.getId())
                .fullName(users_models.getFullName())
                .passWord(users_models.getPassWord())
                .age(users_models.getAge())
                .email(users_models.getEmail())
                .sex(users_models.isSex())
                .role(users_models.getRole())
                .image(users_models.getImage())
                .address(users_models.getAddress())
                .dateOfBirth(users_models.getDateOfBirth())
                .phone(users_models.getPhone())
                .build();
    }

    public Authenticated toAuthenticated(Users_Models users_models) {
        return Authenticated.builder()
                .id(users_models.getId())
                .fullName(users_models.getFullName())
                .passWord(users_models.getPassWord())
                .age(users_models.getAge())
                .email(users_models.getEmail())
                .sex(users_models.isSex())
                .role(users_models.getRole())
                .image(users_models.getImage())
                .address(users_models.getAddress())
                .dateOfBirth(users_models.getDateOfBirth())
                .phone(users_models.getPhone())
                .build();
    }

    public UsersRequest toUsersRequest(Users_Models users_models) {
        return UsersRequest.builder()
                .fullName(users_models.getFullName())
                .age(users_models.getAge())
                .email(users_models.getEmail())
                .sex(users_models.isSex())
                .image(users_models.getImage())
                .address(users_models.getAddress())
                .dateOfBirth(users_models.getDateOfBirth())
                .phone(users_models.getPhone())
                .build();
    }
}