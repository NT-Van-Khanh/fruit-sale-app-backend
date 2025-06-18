package com.example.fruit_selling.service.impl;

import com.example.fruit_selling.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    private StringRedisTemplate redisTemplate;

    // Tạo key Redis với email và mục đích
    private String buildKey(String email, String purpose) {
        System.err.println("OTP_" + purpose + "_" + email);
        return "OTP_" + purpose + "_" + email;
    }

    // Lưu OTP với thời gian sống là 5 phút
    @Override
    public void saveOtp(String email, String purpose, String otp) {
        String key = buildKey(email, purpose);
        redisTemplate.opsForValue().set(key, otp, Duration.ofMinutes(5));
    }

    @Override
    public String getOtp(String email, String purpose) {
        String key = buildKey(email, purpose);
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public void deleteOtp(String email, String purpose) {
        String key = buildKey(email, purpose);
        redisTemplate.delete(key);
    }
}
