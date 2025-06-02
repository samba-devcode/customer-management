package com.tcs.assignment.customer.exception;

/**
 * Enum for application error codes
 */
public enum ErrorCode {

    CUSTOMER_NOT_FOUND(1000, "Customer Not Found"),
    INTERNAL_SERVER_ERROR(1001, "Internal server occured. Please check the logs");

    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
