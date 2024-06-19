package com.iuh.users_healths.Repositories;


import com.iuh.users_healths.Models.Users;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface Users_Repositories extends MongoRepository<Users, String> {
    Users findByUserName(String userName);
    Users findByEmail(String email);
    Users findByUserNameAndEmail(String userName, String email);
}
