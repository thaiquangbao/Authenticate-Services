package com.iuh.users_service.IServices;

import com.iuh.users_service.Dtos.Reponse.Authenticated;
import com.iuh.users_service.Dtos.Reponse.DoctorAuth;
import com.iuh.users_service.Dtos.Reponse.ProfileUsers;
import com.iuh.users_service.Dtos.Reponse.Token;
import com.iuh.users_service.Dtos.Request.*;
import com.iuh.users_service.Dtos.UpdateDoctors;
import com.iuh.users_service.Dtos.UserDto;
import com.iuh.users_service.Dtos.UserLogin;
import com.iuh.users_service.Models.Users_Models;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUsers_Services  extends UserDetailsService {
    ResponseEntity<?> signupUsers (Register register);
    ResponseEntity<?> getAllUsers(Token token);
    ResponseEntity<?> loginAuth(LoginDto loginDtoRequest) throws Exception;
    UserDetails loadUserByUsername(String userName);
    ResponseEntity<?> getUserByUserName(String userName);
    ResponseEntity<?> updateUsers(UpdateUsers updateUsers);
    ResponseEntity<?> generateTokenSignup(GenerateToken generateToken);
    ResponseEntity<?> getUserByToken(Token token);
    UserDto getUserById(Long id);
    ResponseEntity<?> createDoctor(RegisterDoctor register, Token token);
    DoctorAuth getDoctorById(Long id);
    Users_Models updateDoctor(UpdateDoctors updateUsers, Long id);
    boolean deleteOneDoctor(Long id);
    boolean deleteManyDoctor(List<Long> ids);
    UserLogin getOneUser(Long id, Token token);
    int updateOneUser(Long id, UserDtoUpdate userDtoUpdate);
    int checkEmailAndPhone(String phone, String email, Long id);
}
