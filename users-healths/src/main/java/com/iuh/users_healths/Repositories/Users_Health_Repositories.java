package com.iuh.users_healths.Repositories;

import com.iuh.users_healths.Models.Users_Health;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface Users_Health_Repositories extends MongoRepository<Users_Health, String> {
    List<Users_Health> findAllByUsers_UserName(String userName);
}
