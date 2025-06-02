package com.tcs.assignment.customer.exception;

import com.tcs.assignment.generate.model.ErrorResponse;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Global exception handler
 */
@RestControllerAdvice
public class CustomerExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(CustomerExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        logger.error("Internal Server Error occured - {}", ExceptionUtils.getStackTrace(ex));
        ErrorResponse resp = new ErrorResponse();
        resp.setErrorCode(ErrorCode.INTERNAL_SERVER_ERROR.getCode());
        resp.setErrorMessage(ErrorCode.INTERNAL_SERVER_ERROR.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resp);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        logger.error("Validation Exception occured - {}", ExceptionUtils.getStackTrace(ex));
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(CustomerNotFound.class)
    public ResponseEntity<ErrorResponse> customerNotFound(CustomerNotFound cnf){
        logger.error("Customer not found exception occured - {}", ExceptionUtils.getStackTrace(cnf));
        ErrorResponse resp = new ErrorResponse();
        resp.setErrorCode(ErrorCode.CUSTOMER_NOT_FOUND.getCode());
        resp.setErrorMessage(ErrorCode.CUSTOMER_NOT_FOUND.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resp);
    }

}
