package com.example.Restaurant_Manager_BE.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {

    INVALID_INPUT_VALUE(400, "Invalid input value", HttpStatus.BAD_REQUEST),
    EMPTY_INPUT_VALUE(400, "Empty input value", HttpStatus.BAD_REQUEST);


    private int code;
    private String message;
    private HttpStatusCode statusCode;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }
}
