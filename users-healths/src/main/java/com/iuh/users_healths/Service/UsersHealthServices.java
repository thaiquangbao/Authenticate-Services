package com.iuh.users_healths.Service;

import com.iuh.users_healths.Clients.HealthClient;
import com.iuh.users_healths.Dtos.Reponse.UserDto;
import com.iuh.users_healths.Dtos.Reponse.Users_Health_Reponse;
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
    @Autowired
    private HealthClient healthClient;
    @Override
    public UsersRequest create(UsersRequest usersRequest) {
        Users usersCheck = users_repositories.findByUserName(usersRequest.getUserName());
        if(usersCheck != null){
            return null;
        }
        Users users = usersMapper.toEntity(usersRequest);

        return usersMapper.toUserDto(users_repositories.save(users));
    }

    @Override
    public UserDto findOneByUserName(String userName) {
        Users users = users_repositories.findByUserName(userName);
        if(users == null) {
            return null;
        }
        return usersMapper.toUserDtos(users);
    }

    @Override
    public ResponseEntity<?> findAllHealthOfUser(String userName) {
        Users users = users_repositories.findByUserName(userName);
        if(users == null){
          return ResponseEntity.badRequest().body("User not found");
        }
        if(healthClient.findAllUsers(userName).isEmpty()){
            return ResponseEntity.ok("User not found health");
        }
        Users_Health_Reponse users_health_reponse = usersMapper.toUsersHealthReponse(users, healthClient.findAllUsers(userName));

        return ResponseEntity.ok(users_health_reponse);
    }
}
