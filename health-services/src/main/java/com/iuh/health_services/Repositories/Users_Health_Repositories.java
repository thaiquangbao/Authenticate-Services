package com.iuh.health_services.Repositories;

import com.iuh.health_services.Models.Users_Health;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface Users_Health_Repositories extends MongoRepository<Users_Health, ObjectId> {
}
