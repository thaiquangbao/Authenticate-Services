package com.iuh.health_services.Services;

import com.iuh.health_services.Client.UsersClient;
import com.iuh.health_services.Dtos.Request.Healths_Request;
import com.iuh.health_services.Dtos.Request.Users_Healths_Request;
import com.iuh.health_services.Dtos.Respone.Health_Respone;
import com.iuh.health_services.Dtos.Respone.Health_Status;
import com.iuh.health_services.Dtos.Respone.Users_Healths_Response;
import com.iuh.health_services.Dtos.UserDto;
import com.iuh.health_services.IServices.Impl_Users_Health_Services;
import com.iuh.health_services.Kafka.HealthProducer;
import com.iuh.health_services.Mapper.HealthsMapper;
import com.iuh.health_services.Mapper.UsersMapper;
import com.iuh.health_services.Mapper.Users_Health_Mapper;
import com.iuh.health_services.Models.Healths;
import com.iuh.health_services.Models.Users_Health;
import com.iuh.health_services.Repositories.Healths_Repositories;
import com.iuh.health_services.Repositories.Users_Health_Repositories;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.time.ZonedDateTime;
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
    @Autowired
    private UsersClient usersClient;
    @Autowired
    private HealthProducer informationHealth;
    @Autowired
    private HealthsMapper healthsMapper;
    @Autowired
    private Healths_Repositories healthsRepositories;
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
    public List<Health_Status> findAllHealthWithUsers(String userName) {
        List<Health_Status> models = new ArrayList<>();
        List<Users_Health> users_healths = users_health_repositories.findAllByUsers_UserName(userName);
        for (Users_Health users_health : users_healths) {
            models.add(users_health_mapper.toHealthStatus(users_health));
        }
        return models;
    }
    // trạng thái sức khỏe: Tốt, Khá tốt, Không tốt
    @Override
    public ResponseEntity<?> save(Healths_Request healthsRequest) {
        try {
            UserDto users = usersClient.findOneByUserName(healthsRequest.getUserName());
            if(users == null) {
                return ResponseEntity.badRequest().body("User is not exist!!!");
            }
//            Set<String> validConditions = new HashSet<>(Arrays.asList("Good", "Quite Good", "Not Good"));
//            if (!validConditions.contains(users_healths_request.getHealth_condition())) {
//                return ResponseEntity.badRequest().body("Trạng thái sức khỏe không hợp lệ");
//            }
//            Users_Health users_health = users_health_mapper.toUsersHealthEntity(users_healths_request, users);
//            ZonedDateTime dateTime = ZonedDateTime.now(ZoneId.of("Asia/Ho_Chi_Minh"));
//            users_health.setCreated_at(dateTime.toString().substring(0, 16));
//            Users_Health dataSaved = users_health_repositories.save(users_health);
//            Users_Healths_Response convert = users_health_mapper.toUsersHealthResponse(dataSaved);
            Healths healthMappers = healthsMapper.toEntity(healthsRequest);
            Health_Respone convert = healthsMapper.toResponse(healthsRepositories.save(healthMappers));
            informationHealth.sendHealthSuggest(healthsMapper.toDto(convert));
            return ResponseEntity.ok(convert);
        } catch (Exception e) {
            System.out.println(e);
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
