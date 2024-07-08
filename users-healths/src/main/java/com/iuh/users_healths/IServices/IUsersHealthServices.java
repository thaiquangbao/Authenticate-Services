package com.iuh.users_healths.IServices;

import com.iuh.users_healths.Dtos.HealthDto;
import com.iuh.users_healths.Dtos.Reponse.UserDto;
import com.iuh.users_healths.Dtos.Reponse.Users_Healths_Response;
import com.iuh.users_healths.Dtos.Resquest.UsersRequest;
import org.springframework.http.ResponseEntity;

import java.util.concurrent.CompletableFuture;


public interface IUsersHealthServices {
    public UserDto create(UsersRequest usersRequest);
    public UserDto findOneByUserName(String userName);
    public CompletableFuture<ResponseEntity<?>> findAllHealthOfUser(String userName);
    public Users_Healths_Response saveHealthOfUser(HealthDto healthDto);
}
