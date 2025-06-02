package com.tcs.assignment.customer.exception;

public class CustomerNotFound extends RuntimeException{

    private final ErrorCode errorCode;
    public CustomerNotFound(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
