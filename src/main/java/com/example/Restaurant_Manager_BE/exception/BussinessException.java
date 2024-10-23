package com.example.Restaurant_Manager_BE.exception;

import com.example.Restaurant_Manager_BE.exception.ErrorCode;

public class BussinessException extends RuntimeException {
    private ErrorCode errorCode;

    public BussinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
