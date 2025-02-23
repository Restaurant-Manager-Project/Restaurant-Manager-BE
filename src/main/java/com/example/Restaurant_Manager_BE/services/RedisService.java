package com.example.Restaurant_Manager_BE.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RedisService {
    private final RedisTemplate<String, Object> redisTemplate;


    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public void exists(String key) {
        redisTemplate.hasKey(key);
    }

    public void setTimeToLive(String key, long expire) {
        redisTemplate.expire(key, expire, TimeUnit.SECONDS);
    }
}
