package com.iuh.users_service.Mapper;

import com.iuh.users_service.Dtos.*;
import com.iuh.users_service.Dtos.Reponse.*;
import com.iuh.users_service.Dtos.Request.*;
import com.iuh.users_service.Models.Users_Models;
import org.springframework.stereotype.Service;

import java.util.List;

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
                .id(users_models.getId())
                .fullName(users_models.getFullName())
                .passWord(users_models.getPassWord())
                .role(users_models.getRole())
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

    public UserLogin userDtoUpdate(Users_Models users_models, Token token) {
        UserDtoUpdate  userDtoUpdate = UserDtoUpdate.builder()
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
        return UserLogin.builder()
                .data(userDtoUpdate)
                .token(token)
                .build();
    }
    public Register toRegisterDoctor(RegisterDoctor registerDoctor) {
        return Register.builder()
                .fullName(registerDoctor.getFullName())
                .email(registerDoctor.getEmail())
                .passWord(registerDoctor.getPassWord())
                .sex(registerDoctor.isSex())
                .image(registerDoctor.getImage())
                .address(registerDoctor.getAddress())
                .dateOfBirth(registerDoctor.getDateOfBirth())
                .phone(registerDoctor.getPhone())
                .processSignup(registerDoctor.getProcessSignup())
                .role(registerDoctor.getRole())
                .build();
    }
    public DoctorDto toDoctorDto(DoctorFeign registerDoctor, List<Rating> rating) {
        return DoctorDto.builder()
                .id(registerDoctor.getId_user())
                .fullName(registerDoctor.getFullName())
                .email(registerDoctor.getEmail())
                .passWord(registerDoctor.getPassWord())
                .sex(registerDoctor.isSex())
                .image(registerDoctor.getImage())
                .address(registerDoctor.getAddress())
                .dateOfBirth(registerDoctor.getDateOfBirth())
                .phone(registerDoctor.getPhone())
                .processSignup(registerDoctor.getProcessSignup())
                .role(registerDoctor.getRole())
                .specialize(registerDoctor.getSpecialize())
                .rating(rating)
                .build();
    }
    public DoctorLogin toDoctorLogin(DoctorDto doctorDto, Token token) {
        return DoctorLogin.builder()
                .data(doctorDto)
                .token(token)
                .build();
    }
    public DoctorAuth toDoctorAuth(DoctorFeign doctorFeign) {
        return DoctorAuth.builder()
                .id(doctorFeign.getId_user())
                .fullName(doctorFeign.getFullName())
                .email(doctorFeign.getEmail())
                .passWord(doctorFeign.getPassWord())
                .sex(doctorFeign.isSex())
                .image(doctorFeign.getImage())
                .address(doctorFeign.getAddress())
                .dateOfBirth(doctorFeign.getDateOfBirth())
                .phone(doctorFeign.getPhone())
                .processSignup(doctorFeign.getProcessSignup())
                .role(doctorFeign.getRole())
                .specialize(doctorFeign.getSpecialize())
                .rating(doctorFeign.getRating())
                .build();
    }
    public DoctorLogin doctorChecktoDoctorLogin(DoctorDtoCheck doctorCheck, Token token) {
        DoctorDto doctorDto = DoctorDto.builder()
                .id(doctorCheck.getData().getId())
                .fullName(doctorCheck.getData().getFullName())
                .email(doctorCheck.getData().getEmail())
                .passWord(doctorCheck.getData().getPassword())
                .sex(doctorCheck.getData().isSex())
                .image(doctorCheck.getData().getImage())
                .address(doctorCheck.getData().getAddress())
                .dateOfBirth(doctorCheck.getData().getDateOfBirth())
                .phone(doctorCheck.getData().getPhone())
                .processSignup(doctorCheck.getData().getProcessSignup())
                .role(doctorCheck.getData().getRole())
                .specialize(doctorCheck.getData().getSpecialize())
                .rating(doctorCheck.getData().getRating())
                .build();
        return DoctorLogin.builder()
                .data(doctorDto)
                .token(token)
                .build();
    }
    public GetAllUsers toGetAllUsers(List<ProfileUsers> users_models, Token token) {
        return GetAllUsers.builder()
                .data(users_models)
                .token(token)
                .build();
    }
    public DoctorReponse doctorReponse(UsersRequest doctorDto, Token token) {
        return DoctorReponse.builder()
                .data(doctorDto)
                .token(token)
                .build();
    }
    public Users_Models toDoctorModelUpdate(UpdateDoctors updateUsers) {
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
    public Users_Models toUserUpdateModel(UserDtoUpdate updateUsers) {
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
                .role(updateUsers.getRole())
                .processSignup(updateUsers.getProcessSignup())
                .build();
    }
    public UserDtoUpdate toUserModelUpdate(Users_Models updateUsers) {
        return UserDtoUpdate.builder()
                .id(updateUsers.getId())
                .fullName(updateUsers.getFullName())
                .email(updateUsers.getEmail())
                .sex(updateUsers.isSex())
                .passWord(updateUsers.getPassWord())
                .image(updateUsers.getImage())
                .address(updateUsers.getAddress())
                .dateOfBirth(updateUsers.getDateOfBirth())
                .phone(updateUsers.getPhone())
                .role(updateUsers.getRole())
                .processSignup(updateUsers.getProcessSignup())
                .build();
    }
}