package com.example.Restaurant_Manager_BE.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler{
    @ExceptionHandler(BussinessException.class)
    public ResponseEntity<MessageResponse> handleTableException(BussinessException e) {
        ErrorCode errorCode = e.getErrorCode();
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setSuccess(false);
        messageResponse.setCode(errorCode.getCode());
        messageResponse.setMessage(errorCode.getMessage());

        return new ResponseEntity<>(messageResponse, errorCode.getStatusCode());
    }

}
