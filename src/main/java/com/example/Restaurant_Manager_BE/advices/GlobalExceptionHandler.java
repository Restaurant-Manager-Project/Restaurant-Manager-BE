package com.example.Restaurant_Manager_BE.advices;

import com.example.Restaurant_Manager_BE.exceptions.DataNotFoundException;
import com.example.Restaurant_Manager_BE.exceptions.ErrorCode;
import com.example.Restaurant_Manager_BE.responses.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler{
    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<APIResponse> handleDataNotFoundException(DataNotFoundException ex){
        APIResponse apiResponse = new APIResponse();
        apiResponse.setSuccess(false);
        apiResponse.setCode(404);
        apiResponse.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
    }



}
