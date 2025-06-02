package com.tcs.assignment.customer;


import com.tcs.assignment.customer.exception.CustomerExceptionHandler;
import com.tcs.assignment.customer.exception.CustomerNotFound;
import com.tcs.assignment.customer.exception.ErrorCode;
import com.tcs.assignment.generate.model.ErrorResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class CustomerExceptionHandlerTest {


    @InjectMocks
    private CustomerExceptionHandler mainClass = new CustomerExceptionHandler();

    @Test
    void testHandleGenericException(){
        String str = "Generic exception occured";
        ResponseEntity<ErrorResponse> response = mainClass.handleGenericException(new NullPointerException(str));

        assertNotNull(response);
        assertEquals(ErrorCode.INTERNAL_SERVER_ERROR.getMessage(), response.getBody().getErrorMessage());
    }

    @Test
    void testcustomerNotFound(){
        String str = "Generic exception occured";
        ResponseEntity<ErrorResponse> response = mainClass.customerNotFound(new CustomerNotFound(ErrorCode.CUSTOMER_NOT_FOUND));

        assertNotNull(response);
        assertEquals(ErrorCode.CUSTOMER_NOT_FOUND.getMessage(), response.getBody().getErrorMessage());
    }
}
