package com.tcs.assignment.customer;

import com.tcs.assignment.customer.controller.CustomerController;
import com.tcs.assignment.customer.entity.Customer;
import com.tcs.assignment.customer.service.CustomerService;
import com.tcs.assignment.generate.model.CustomerRequest;
import com.tcs.assignment.generate.model.CustomerResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


/**
 * Test class for controller
 */
@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController mainClass = new CustomerController();

    private CustomerRequest createReq;

    private final UUID uuid = UUID.randomUUID();
    private Customer customer = null;

    @BeforeEach
    void setUp(){
        createReq = createReq();
        customer = customer();
    }

    @Test
    void testCreateCustomer(){
        when(customerService.createCustomer(any())).thenReturn(customer);

        ResponseEntity<Void> response = mainClass.createCustomer(createReq);

        assertNotNull(response);
        assertEquals(response.getStatusCode().value(), 200);

    }

    @Test
    void testCustomerById(){
        when(customerService.getCustomer(any())).thenReturn(customer);

        ResponseEntity<CustomerResponse> response = mainClass.customerById(uuid.toString());

        assertNotNull(response);
        assertEquals(response.getStatusCode().value(), 200);

    }


    @Test
    void testcustomerByName(){
        when(customerService.getCustomerByName(any())).thenReturn(customer);

        ResponseEntity<CustomerResponse> response = mainClass.customerByNameOrEmail(createReq.getName(), null);

        assertNotNull(response);
        assertEquals(response.getStatusCode().value(), 200);

    }

    @Test
    void testcustomerByEmail(){
        when(customerService.getCustomerByEmail(any())).thenReturn(customer);

        ResponseEntity<CustomerResponse> response = mainClass.customerByNameOrEmail(null, createReq.getEmail());

        assertNotNull(response);
        assertEquals(response.getStatusCode().value(), 200);

    }


    @Test
    void testdeleteCustomer(){
       // when(customerService.deleteCustomer(any()).thenReturn(customer);

        ResponseEntity<Void> response = mainClass.deleteCustomer(UUID.randomUUID().toString());

        assertNotNull(response);
        assertEquals(response.getStatusCode().value(), 204);

    }

    @Test
    void testupdateCustomer(){
        when(customerService.updateCustomer(any(), any())).thenReturn(customer);

        ResponseEntity<CustomerResponse> response = mainClass.updateCustomer(uuid.toString(), createReq);

        assertNotNull(response);

    }

    private Customer customer(){
        return new Customer(uuid, createReq.getName(), createReq.getEmail(), createReq.getAnnualSpend(), createReq.getLastPurchaseDate());
    }

    private CustomerRequest createReq(){
        CustomerRequest request = new CustomerRequest();
        request.setAnnualSpend(100d);
        request.setEmail("test@test.com");
        request.setName("Test");
        request.setLastPurchaseDate(new Date());
        return request;
    }
}
