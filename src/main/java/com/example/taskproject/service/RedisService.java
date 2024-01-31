package com.example.taskproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    private final RedisTemplate<String, String> redisTemplate;

    @Autowired
    public RedisService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void saveDataToRedis(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public String getDataFromRedis(String key) {
        String value = redisTemplate.opsForValue().get(key);
        if (value == null) {
            return "Key not found";
        }
        return value;
    }
}