package com.iuh.health_services.Repositories;

import com.iuh.health_services.Models.Users_Health;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface Users_Health_Repositories extends MongoRepository<Users_Health, String> {
    List<Users_Health> findAllByUsers_UserName(String userName);
}
