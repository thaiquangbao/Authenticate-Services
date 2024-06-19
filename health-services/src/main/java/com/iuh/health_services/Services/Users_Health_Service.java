package com.iuh.health_services.Services;

import com.iuh.health_services.Dtos.Request.Users_Healths_Request;
import com.iuh.health_services.Dtos.Respone.Users_Healths_Response;
import com.iuh.health_services.IServices.Impl_Users_Health_Services;
import com.iuh.health_services.Mapper.UsersMapper;
import com.iuh.health_services.Mapper.Users_Health_Mapper;
import com.iuh.health_services.Models.Users;
import com.iuh.health_services.Models.Users_Health;
import com.iuh.health_services.Repositories.Users_Health_Repositories;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class Users_Health_Service implements Impl_Users_Health_Services {
    @Autowired
    private Users_Health_Repositories users_health_repositories;
    @Autowired
    private Users_Health_Mapper users_health_mapper;
    @Autowired
    private UsersMapper usersMapper;
    @Override
    public List<Users_Healths_Response>findAll() {
        List<Users_Healths_Response> models = new ArrayList<>();
        List<Users_Health> users_healths = users_health_repositories.findAll();
        for(Users_Health users_health : users_healths) {
            models.add(users_health_mapper.toUsersHealthResponse(users_health));
        }
        return models;
    }

    @Override
    public Users_Health findByEmail(String email) {

        return null;
    }
    public Users_Healths_Response findAllHealthWithUsers(String id) {
        Users_Health users_health = users_health_repositories.findById(id)
                .orElse(Users_Health.builder()
                        .users(Users.builder()
                                .userName("NOT_FOUND")
                                .fullName("NOT_FOUND")
                                .email("NOT_FOUND")
                                .build())
                        .build());
        if(users_health == null) {
            return null;
        }
        return users_health_mapper.toUsersHealthResponse(users_health);
    }
    // trạng thái sức khỏe: Tốt, Khá tốt, Không tốt
    @Override
    public ResponseEntity<?> save(Users_Healths_Request users_healths_request) {
        try {
            Set<String> validConditions = new HashSet<>(Arrays.asList("Good", "Quite Good", "Not Good"));
            System.out.println(validConditions);
            System.out.println(users_healths_request.getHealth_condition());
            if (!validConditions.contains(users_healths_request.getHealth_condition())) {
                return ResponseEntity.badRequest().body("Trạng thái sức khỏe không hợp lệ");
            }
            Users_Health users_health = users_health_mapper.toUsersHealthEntity(users_healths_request);
            Users_Health dataSaved = users_health_repositories.save(users_health);
            Users_Healths_Response convert = users_health_mapper.toUsersHealthResponse(dataSaved);
            return ResponseEntity.ok(convert);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error when save users health!!!");
        }

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Users_Health update(Users_Health users_health) {
        return null;
    }
}
