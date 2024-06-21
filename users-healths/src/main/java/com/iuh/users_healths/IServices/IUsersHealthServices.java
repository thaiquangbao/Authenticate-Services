package com.iuh.users_healths.IServices;

import com.iuh.users_healths.Dtos.Reponse.UserDto;
import com.iuh.users_healths.Dtos.Reponse.Users_Health_Reponse;
import com.iuh.users_healths.Dtos.Resquest.UsersRequest;
import org.springframework.http.ResponseEntity;


public interface IUsersHealthServices {
    public UsersRequest create(UsersRequest usersRequest);
    public UserDto findOneByUserName(String userName);
    public ResponseEntity<?> findAllHealthOfUser(String userName);
}
