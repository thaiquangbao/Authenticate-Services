package com.iuh.users_healths.Service;

import com.iuh.users_healths.Clients.AuthClient;
import com.iuh.users_healths.Dtos.Resquest.UsersRequest;
import com.iuh.users_healths.IServices.IUsersHealthServices;
import com.iuh.users_healths.Mappers.UsersMappers;
import com.iuh.users_healths.Models.Users;
import com.iuh.users_healths.Repositories.Users_Repositories;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UsersHealthServices implements IUsersHealthServices {
    @Autowired
    private Users_Repositories  users_repositories;
    @Autowired
    private UsersMappers usersMapper;
    @Override
    public UsersRequest create(UsersRequest usersRequest) {
        Users usersCheck = users_repositories.findByUserName(usersRequest.getUserName());
        if(usersCheck != null){
            return null;
        }
        Users users = usersMapper.toEntity(usersRequest);

        return usersMapper.toUserDto(users_repositories.save(users));
    }
}
