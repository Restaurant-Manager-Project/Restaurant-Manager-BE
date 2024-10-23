package com.example.Restaurant_Manager_BE.exceptions;

import com.example.Restaurant_Manager_BE.responses.APIResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler{
    @ExceptionHandler(BussinessException.class)
    public ResponseEntity<APIResponse> handleTableException(BussinessException e) {
        ErrorCode errorCode = e.getErrorCode();
        APIResponse APIResponse = new APIResponse();
        APIResponse.setSuccess(false);
        APIResponse.setCode(errorCode.getCode());
        APIResponse.setMessage(errorCode.getMessage());

        return new ResponseEntity<>(APIResponse, errorCode.getStatusCode());
    }

}
