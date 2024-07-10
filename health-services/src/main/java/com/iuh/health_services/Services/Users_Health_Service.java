package com.iuh.health_services.Services;

import com.iuh.health_services.Client.UsersClient;
import com.iuh.health_services.Dtos.Health_Redis;
import com.iuh.health_services.Dtos.Request.Healths_Request;
import com.iuh.health_services.Dtos.Respone.Health_Response;
import com.iuh.health_services.Dtos.Respone.Health_Status_Feign;
import com.iuh.health_services.Dtos.UserDto;
import com.iuh.health_services.IServices.Impl_Users_Health_Services;
import com.iuh.health_services.Kafka.HealthProducer;
import com.iuh.health_services.Mapper.HealthsMapper;
import com.iuh.health_services.Mapper.UsersMapper;
import com.iuh.health_services.Models.Healths;
import com.iuh.health_services.Redis.Service_Redis.Health_Service_Redis;
import com.iuh.health_services.Repositories.Healths_Repositories;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class Users_Health_Service implements Impl_Users_Health_Services {
    private static final Logger log = LoggerFactory.getLogger(Users_Health_Service.class);
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
    @Autowired
    private Health_Service_Redis healthServiceRedis;
    private static final String HEALTH_KEY = "HEALTH";

    public List<Health_Status_Feign> findAllHealthWithUsers(String userName) {
        List<Health_Status_Feign> models = new ArrayList<>();
        List<Healths> users_healths = healthsRepositories.findAllByUserName(userName);
        for (Healths users_health : users_healths) {
            models.add(healthsMapper.toHealthStatus(users_health));
        }
        return models;
    }

    @Override
    public Health_Redis findOne(String id_redis) {

        return healthServiceRedis.findUserById("HEALTH",id_redis);
    }

    @Override
    public List<Health_Redis> findAll() {
        return healthServiceRedis.findAllHealth("HEALTH");
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
            boolean redis = healthServiceRedis.saveHealth(healthsMapper.toHealthRedis(healthMappers), HEALTH_KEY);

            if (!redis) {
                return ResponseEntity.badRequest().body("Error when save users health to redis!!!");
            }
            Health_Response convert = healthsMapper.toResponse(healthsRepositories.save(healthMappers));
            informationHealth.sendHealthSuggest(healthsMapper.toDto(convert));
            return ResponseEntity.ok("Sending health suggest success!!!");
        } catch (Exception e) {
            System.out.println(e);
            return ResponseEntity.badRequest().body("Error when save users health!!!");
        }

    }

    @Override
    public boolean delete(String id) {

        try {
            healthServiceRedis.deleteHealth("HEALTH",id);
            return true;
        } catch (Exception e) {
            log.error("Error in deleting data from redis: ",e);
            return false;
        }

    }

    @Override
    public boolean update(String id, Health_Redis healthRedis) {
        return healthServiceRedis.updateUser("HEALTH",id,healthRedis);
    }

}
