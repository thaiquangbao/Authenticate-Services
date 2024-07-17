package com.iuh.users_service.IServices;

import com.iuh.users_service.Dtos.Reponse.Authenticated;
import com.iuh.users_service.Dtos.Reponse.ProfileUsers;
import com.iuh.users_service.Dtos.Reponse.Token;
import com.iuh.users_service.Dtos.Request.*;
import com.iuh.users_service.Dtos.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUsers_Services  extends UserDetailsService {
    ResponseEntity<?> signupUsers (Register register);
    List<ProfileUsers> getAllUsers();
    ResponseEntity<?> loginAuth(LoginDto loginDtoRequest) throws Exception;
    UserDetails loadUserByUsername(String userName);
   // Authenticated refreshToken(Authenticated authenticated) throws Exception;
    ResponseEntity<?> getUserByUserName(String userName);
    ResponseEntity<?> updateUsers(UpdateUsers updateUsers);
    ResponseEntity<?> generateTokenSignup(GenerateToken generateToken);
    ResponseEntity<?> getUserByToken(Token token);
    UserDto getUserById(Long id);
}
