package com.iuh.users_service.Mapper;

import com.iuh.users_service.Dtos.Reponse.Authenticated;
import com.iuh.users_service.Dtos.Reponse.ProfileUsers;
import com.iuh.users_service.Dtos.Request.Register;
import com.iuh.users_service.Dtos.Request.UpdateUsers;
import com.iuh.users_service.Dtos.Request.UsersRequest;
import com.iuh.users_service.Dtos.UserDto;
import com.iuh.users_service.Dtos.UserUntill;
import com.iuh.users_service.Models.Users_Models;
import org.springframework.stereotype.Service;

@Service
public class Users_Mapper {
    public Users_Models toUsersEntity(Register register) {
        return Users_Models.builder()
                .fullName(register.getFullName())
                .passWord(register.getPassWord())
                .email(register.getEmail())
                .sex(register.isSex())
                .role(register.getRole())
                .image(register.getImage())
                .address(register.getAddress())
                .dateOfBirth(register.getDateOfBirth())
                .phone(register.getPhone())
                .processSignup(register.getProcessSignup())
                .build();
    }

    public ProfileUsers toProfileUsers(Users_Models users_models) {
        return ProfileUsers.builder()
                .id(users_models.getId())
                .fullName(users_models.getFullName())
                .passWord(users_models.getPassWord())
                .email(users_models.getEmail())
                .sex(users_models.isSex())
                .role(users_models.getRole())
                .image(users_models.getImage())
                .address(users_models.getAddress())
                .dateOfBirth(users_models.getDateOfBirth())
                .phone(users_models.getPhone())
                .processSignup(users_models.getProcessSignup())
                .build();
    }

    public Authenticated toAuthenticated(Users_Models users_models) {
        return Authenticated.builder()
                .id(users_models.getId())
                .fullName(users_models.getFullName())
                .passWord(users_models.getPassWord())
                .email(users_models.getEmail())
                .sex(users_models.isSex())
                .role(users_models.getRole())
                .image(users_models.getImage())
                .address(users_models.getAddress())
                .dateOfBirth(users_models.getDateOfBirth())
                .phone(users_models.getPhone())
                .processSignup(users_models.getProcessSignup())
                .build();
    }

    public UsersRequest toUsersRequest(Users_Models users_models) {
        return UsersRequest.builder()
                .fullName(users_models.getFullName())
                .email(users_models.getEmail())
                .sex(users_models.isSex())
                .image(users_models.getImage())
                .address(users_models.getAddress())
                .dateOfBirth(users_models.getDateOfBirth())
                .phone(users_models.getPhone())
                .processSignup(users_models.getProcessSignup())
                .build();
    }
    public Users_Models toUserModelUpdate(UpdateUsers updateUsers) {
        return Users_Models.builder()
                .id(updateUsers.getId())
                .fullName(updateUsers.getFullName())
                .email(updateUsers.getEmail())
                .sex(updateUsers.isSex())
                .passWord(updateUsers.getPassWord())
                .image(updateUsers.getImage())
                .address(updateUsers.getAddress())
                .dateOfBirth(updateUsers.getDateOfBirth())
                .phone(updateUsers.getPhone())
                .processSignup(updateUsers.getProcessSignup())
                .build();
    }
    public UserUntill toUserUntill(Authenticated authenticated) {
        UserDto userDto = UserDto.builder()
                .id(authenticated.getId())
                .address(authenticated.getAddress())
                .sex(authenticated.isSex())
                .image(authenticated.getImage())
                .role(authenticated.getRole())
                .dateOfBirth(authenticated.getDateOfBirth())
                .phone(authenticated.getPhone())
                .fullName(authenticated.getFullName())
                .email(authenticated.getEmail())
                .processSignup(authenticated.getProcessSignup())
                .passWord(authenticated.getPassWord1())
                .build();
        return UserUntill.builder()
                .data(userDto)
                .token(authenticated.getToken())
                .build();
    }
    public UserDto toUserDto(Users_Models users_models) {
        return UserDto.builder()
                .id(users_models.getId())
                .fullName(users_models.getFullName())
                .email(users_models.getEmail())
                .passWord(users_models.getPassWord())
                .sex(users_models.isSex())
                .image(users_models.getImage())
                .address(users_models.getAddress())
                .dateOfBirth(users_models.getDateOfBirth())
                .phone(users_models.getPhone())
                .processSignup(users_models.getProcessSignup())
                .role(users_models.getRole())
                .build();
    }
}