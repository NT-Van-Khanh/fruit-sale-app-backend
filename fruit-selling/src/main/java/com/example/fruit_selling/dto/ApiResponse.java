package com.example.fruit_selling.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;


@Builder
@Data
public class ApiResponse <T>{
    private int statusCode;
    private String message;
    private T data;

    public ApiResponse() {
    }

    public ApiResponse(int statusCode, String message, T data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

    public ApiResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
    public ApiResponse(HttpStatus httpStatus, T data) {
        this.statusCode = httpStatus.value();
        this.message = httpStatus.getReasonPhrase();
        this.data = data;
    }
}
