package com.iuh.health_services.Redis.Service_Redis;

import com.iuh.health_services.Dtos.Health_Redis;
import org.bson.types.ObjectId;

import java.util.List;

public interface Health_Service_Redis {
    boolean saveHealth(Health_Redis healthRedis,String key);

    List<Health_Redis> findAllHealth(String key);

    Health_Redis findUserById(String key,String id);

    boolean deleteHealth(String key, String id);

    boolean updateUser(String key, String id, Health_Redis healthRedis);
}
