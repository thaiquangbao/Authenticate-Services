package com.iuh.users_healths.Service;

import com.iuh.users_healths.Clients.HealthClient;
import com.iuh.users_healths.Dtos.HealthDto;
import com.iuh.users_healths.Dtos.Reponse.*;
import com.iuh.users_healths.Dtos.Resquest.UsersRequest;
import com.iuh.users_healths.IServices.IUsersHealthServices;
import com.iuh.users_healths.Mappers.UsersMappers;
import com.iuh.users_healths.Mappers.Users_Health_Mapper;
import com.iuh.users_healths.Models.Users;
import com.iuh.users_healths.Repositories.Users_Health_Repositories;
import com.iuh.users_healths.Repositories.Users_Repositories;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CompletableFuture;


@Service
@Transactional
@RequiredArgsConstructor
public class UsersHealthServices implements IUsersHealthServices {
    private static final Logger log = LoggerFactory.getLogger(UsersHealthServices.class);
    @Autowired
    private Users_Repositories  users_repositories;
    @Autowired
    private UsersMappers usersMapper;
    @Autowired
    private HealthClient healthClient;
    @Autowired
    private Healths_Status_Res healthsStatusRes;
    @Autowired
    private Users_Health_Repositories usersHealthRepositories;
    @Autowired
    private Users_Health_Mapper usersHealthMapper;
    @Autowired
    private UsersHealthCheckOfAgeMan usersHealthCheckOfAgeMan;
    @Autowired
    private UsersHealthCheckOfAgeWoman usersHealthCheckOfAgeWoman;
    @Override
    public UserDto create(UsersRequest usersRequest) {
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
    @Async
    @Override
    @CircuitBreaker(name = "health-service", fallbackMethod = "replaceHealthOfUser") //dùng để xử lý  lỗi khi gọi api health bi gián đoạn
    @TimeLimiter(name = "health-service") //  replaceHealthOfUser là hàm xử lý khi gọi api health bị gián đoạn
    @Retry(name = "health-service")
    public CompletableFuture<ResponseEntity<?>> findAllHealthOfUser(String userName) {
        return CompletableFuture.supplyAsync(() -> {
            Users users = users_repositories.findByUserName(userName);
            if (users == null) {
                return ResponseEntity.badRequest().body("User not found");
            }
            if (healthClient.findAllUsers(userName).isEmpty()) {
                return ResponseEntity.ok("User not found health");
            }
            Users_Health_Feign healthStatusFeign = usersMapper.toUsersHealthFeign(users, healthClient.findAllUsers(userName));

            return ResponseEntity.ok(healthStatusFeign);
        }).handle((response, ex) -> {
            if (ex != null) {
                return handleException(ex);
            }
            return response;
        });

    }
    private ResponseEntity<?> handleException(Throwable ex) {
        log.warn("Exception occurred: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Service is having some issue, please try again later!!!");
    }
    public CompletableFuture<ResponseEntity<?>> replaceHealthOfUser(String userName, Throwable throwable) {
        log.warn("Fallback method called when calling health service from users-health due to: {}", throwable.getMessage());
        return CompletableFuture.completedFuture(
                ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Service is having some issue, please try again later!!!")
        );
    }
    @Override
    public Users_Healths_Response saveHealthOfUser(HealthDto healthDto) {
        Users users = users_repositories.findByUserName(healthDto.getUserName());
        if(users == null) {
            log.error("User not found");
            return null;
        }
        int ageConvert = Integer.parseInt(users.getAge());
        if (users.isSex()) {
            if (ageConvert >= 15 && ageConvert <= 19) {
                return usersHealthCheckOfAgeMan.age15To19(healthDto, users);
            } else if (ageConvert >= 20 && ageConvert <= 29) {
                return usersHealthCheckOfAgeMan.age20To29(healthDto, users);
            } else if (ageConvert >= 30 && ageConvert <= 39) {
                return usersHealthCheckOfAgeMan.age30To39(healthDto, users);
            } else if (ageConvert >= 40 && ageConvert <= 49) {
                return usersHealthCheckOfAgeMan.age40To49(healthDto, users);
            } else if (ageConvert >= 50 && ageConvert <= 59) {
                return usersHealthCheckOfAgeMan.age50To59(healthDto, users);
            } else if (ageConvert >= 60 && ageConvert <= 69) {
                return usersHealthCheckOfAgeMan.age60To69(healthDto, users);
            } else if (ageConvert >= 70) {
                return usersHealthCheckOfAgeMan.age70Up(healthDto, users);
            } else {
                log.error("User not enough age to check health");
                return null;
            }
        } else {
            if (ageConvert >= 15 && ageConvert <= 19) {
                return usersHealthCheckOfAgeWoman.age15To19(healthDto, users);
            } else if (ageConvert >= 20 && ageConvert <= 29) {
                return usersHealthCheckOfAgeWoman.age20To29(healthDto, users);
            } else if (ageConvert >= 30 && ageConvert <= 39) {
                return usersHealthCheckOfAgeWoman.age30To39(healthDto, users);
            } else if (ageConvert >= 40 && ageConvert <= 49) {
                return usersHealthCheckOfAgeWoman.age40To49(healthDto, users);
            } else if (ageConvert >= 50 && ageConvert <= 59) {
                return usersHealthCheckOfAgeWoman.age50To59(healthDto, users);
            } else if (ageConvert >= 60 && ageConvert <= 69) {
                return usersHealthCheckOfAgeWoman.age60To69(healthDto, users);
            } else if (ageConvert >= 70) {
                return usersHealthCheckOfAgeWoman.age70Up(healthDto, users);
            } else {
                log.error("User not enough age to check health");
                return null;
            }
        }
    }
}
