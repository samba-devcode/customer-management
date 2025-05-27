package com.tcs.assignment.customer;

import com.tcs.assignment.customer.data.CustomerRepository;
import com.tcs.assignment.customer.entity.Customer;
import com.tcs.assignment.customer.service.CustomerService;
import com.tcs.assignment.generate.model.CustomerRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;
    @InjectMocks
    private CustomerService mainClass = new CustomerService();

    @Test
    void testCreateCustomer(){
        when(customerRepository.save(any())).thenReturn(customer());

        Customer customer = mainClass.createCustomer(createReq());

        assertNotNull(customer);
        assertNull(customer.getId());
    }

    private Customer customer(){
        Customer customer = new Customer();

        return customer;
    }

    private CustomerRequest createReq(){
        CustomerRequest request = new CustomerRequest();

        return request;
    }
}
