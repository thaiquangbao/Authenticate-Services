package com.iuh.health_services.Repositories;

import com.iuh.health_services.Models.Healths;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface Healths_Repositories extends MongoRepository<Healths, String> {
    List<Healths> findAllByUserName(String userName);
}
