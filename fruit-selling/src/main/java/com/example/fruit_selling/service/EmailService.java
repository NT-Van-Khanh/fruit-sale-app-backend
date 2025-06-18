package com.example.fruit_selling.service;


public interface EmailService {
    void sendMail(String to, String subject, String content);
    String generateOtp();
}
