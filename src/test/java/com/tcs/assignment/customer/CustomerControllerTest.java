package com.tcs.assignment.customer;

import com.tcs.assignment.customer.controller.CustomerController;
import com.tcs.assignment.customer.entity.Customer;
import com.tcs.assignment.customer.service.CustomerService;
import com.tcs.assignment.generate.model.CustomerRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController mainClass = new CustomerController();

    private CustomerRequest createReq;

    @BeforeEach
    void setUp(){
        createReq = createReq();
    }

    @Test
    void testCreateCustomer(){
        when(customerService.createCustomer(any())).thenReturn(new Customer());

        ResponseEntity<Void> response = mainClass.createCustomer(createReq);

        assertNotNull(response);
        assertEquals(response.getStatusCode().value(), 204);

    }

    private CustomerRequest createReq(){
        CustomerRequest request = new CustomerRequest();

        return request;
    }
}
