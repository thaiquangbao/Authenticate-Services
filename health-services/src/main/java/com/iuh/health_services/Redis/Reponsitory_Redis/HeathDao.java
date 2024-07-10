package com.iuh.health_services.Redis.Reponsitory_Redis;

import com.iuh.health_services.Dtos.Health_Redis;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Repository
public class HeathDao implements Impl_HealthDao {
    private static final Logger log = LoggerFactory.getLogger(HeathDao.class);
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final String KEY = "HEALTH";
    @Override
    public boolean saveHealth(Health_Redis healthRedis,String key) {
        try {

            redisTemplate.opsForHash().put(key, healthRedis.getId_redis(),healthRedis);

           redisTemplate.expire(key, 120, TimeUnit.SECONDS);
            return true;
        } catch (Exception e) {
            log.error("Error in saving data to redis: ",e);
            return false;
        }

    }

    @Override
    public List<Health_Redis> findAllHealth(String key) {
        List<Object> values = redisTemplate.opsForHash().values(key);
        return values.stream()
                .map(object -> (Health_Redis) object)
                .collect(Collectors.toList());

    }

    @Override
    public Health_Redis findUserById(String key,String id) {
        System.out.println(key);
        return (Health_Redis) redisTemplate.opsForHash().get(key, id);
    }

    @Override
    public boolean deleteHealth(String key,String id) {
        try {
            redisTemplate.opsForHash().delete(key,id);
            return  true;
        } catch (Exception e) {
            log.error("Error in deleting data from redis: ",e);
            return false;
        }
    }

    @Override
    public boolean updateUser(String key, String id, Health_Redis healthRedis) {
        try {
            redisTemplate.opsForHash().put(key, id, healthRedis);
            return  true;
        } catch (Exception e) {
            log.error("Error in deleting data from redis: ",e);
            return false;
        }
    }
}
