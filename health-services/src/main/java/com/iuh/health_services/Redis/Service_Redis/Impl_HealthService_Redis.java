package com.iuh.health_services.Redis.Service_Redis;

import com.iuh.health_services.Dtos.Health_Redis;
import com.iuh.health_services.Redis.Reponsitory_Redis.Impl_HealthDao;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class Impl_HealthService_Redis implements Health_Service_Redis {
    @Autowired
    private Impl_HealthDao healthDao;

    @Override
    public boolean saveHealth(Health_Redis healthRedis,String key) {
        return healthDao.saveHealth(healthRedis,key);
    }

    @Override
    public List<Health_Redis> findAllHealth(String key) {
        return healthDao.findAllHealth(key);
    }

    @Override
    public Health_Redis findUserById(String key, String id) {
        return (Health_Redis) healthDao.findUserById(key, id);
    }

    @Override
    public boolean deleteHealth(String key,String id) {
        return healthDao.deleteHealth(key, id);
    }

    @Override
    public boolean updateUser(String key, String id, Health_Redis healthRedis) {
        return healthDao.updateUser(key,id,healthRedis);
    }
}
