package com.iuh.users_healths.IServices;

import com.iuh.users_healths.Dtos.Resquest.UsersRequest;
import org.springframework.http.ResponseEntity;


public interface IUsersHealthServices {
    public UsersRequest create(UsersRequest usersRequest);
}
