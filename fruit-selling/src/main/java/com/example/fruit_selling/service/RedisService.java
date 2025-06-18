package com.example.fruit_selling.service;

public interface RedisService {
    void saveOtp(String email, String purpose, String otp);
    String getOtp(String email, String purpose);
    void deleteOtp(String email, String purpose) ;
}
