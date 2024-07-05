package com.iuh.health_services.Services;

import com.iuh.health_services.Client.UsersClient;
import com.iuh.health_services.Dtos.Request.Healths_Request;
import com.iuh.health_services.Dtos.Respone.Health_Response;
import com.iuh.health_services.Dtos.Respone.Health_Status_Feign;
import com.iuh.health_services.Dtos.UserDto;
import com.iuh.health_services.IServices.Impl_Users_Health_Services;
import com.iuh.health_services.Kafka.HealthProducer;
import com.iuh.health_services.Mapper.HealthsMapper;
import com.iuh.health_services.Mapper.UsersMapper;
import com.iuh.health_services.Models.Healths;
import com.iuh.health_services.Repositories.Healths_Repositories;
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
    private UsersMapper usersMapper;
    @Autowired
    private UsersClient usersClient;
    @Autowired
    private HealthProducer informationHealth;
    @Autowired
    private HealthsMapper healthsMapper;
    @Autowired
    private Healths_Repositories healthsRepositories;



    public List<Health_Status_Feign> findAllHealthWithUsers(String userName) {
        List<Health_Status_Feign> models = new ArrayList<>();
        List<Healths> users_healths = healthsRepositories.findAllByUserName(userName);
        for (Healths users_health : users_healths) {
            models.add(healthsMapper.toHealthStatus(users_health));
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
            Healths healthMappers = healthsMapper.toEntity(healthsRequest);
            Health_Response convert = healthsMapper.toResponse(healthsRepositories.save(healthMappers));
            informationHealth.sendHealthSuggest(healthsMapper.toDto(convert));
            return ResponseEntity.ok(convert);
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.badRequest().body("Error when save users health!!!");
        }

    }

}
