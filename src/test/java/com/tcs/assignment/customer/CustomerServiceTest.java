package com.tcs.assignment.customer;

import com.tcs.assignment.customer.data.CustomerRepository;
import com.tcs.assignment.customer.entity.Customer;
import com.tcs.assignment.customer.exception.CustomerNotFound;
import com.tcs.assignment.customer.service.CustomerService;
import com.tcs.assignment.generate.model.CustomerRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;
    @InjectMocks
    private CustomerService mainClass = new CustomerService();

    private final UUID uuid = UUID.randomUUID();
    private CustomerRequest customerRequest = null;
    private Customer customer = null;

    @BeforeEach
    public void setup(){
        customerRequest = customerRequest();
        customer = customer();
    }


    @Test
    @DisplayName("Create customer")
    void testCreateCustomer(){
        when(customerRepository.save(any())).thenReturn(customer());

        Customer customer = mainClass.createCustomer(createReq());

        assertNotNull(customer);
        assertEquals(uuid.toString(), customer.getId().toString());
    }

    @Test
    @DisplayName("get customer")
    void testGetCustomer(){

        when(customerRepository.findById(any())).thenReturn(Optional.of(customer()));

        Customer customer = mainClass.getCustomer(uuid);

        assertNotNull(customer);
        assertEquals(uuid.toString(), customer.getId().toString());
    }

    @Test
    @DisplayName("get customer - customer not found")
    void testGetCustomerCustomerNotFound(){

        when(customerRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(CustomerNotFound.class, () -> {
            mainClass.getCustomer(uuid);
        });

    }

    @Test
    @DisplayName("update customer")
    void testUpdateCustomer(){
        when(customerRepository.findById(any())).thenReturn(Optional.of(customer()));
        when(customerRepository.save(any())).thenReturn(customer());

        Customer customer = mainClass.updateCustomer(uuid.toString(), customerRequest);

        assertNotNull(customer);
        assertEquals(uuid.toString(), customer.getId().toString());
        verify(customerRepository).save(any());
    }

    @Test
    @DisplayName("update customer - customer not found")
    void testUpdateCustomerCustomerNotFound(){

        when(customerRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(CustomerNotFound.class, () -> {
            mainClass.getCustomer(uuid);
        });

    }

    @Test
    @DisplayName("delete customer")
    void testDeleteCustomer(){
        when(customerRepository.findById(any())).thenReturn(Optional.of(customer()));

        mainClass.deleteCustomer(uuid);

        verify(customerRepository).deleteById(any());

    }

    @Test
    @DisplayName("delete customer - customer not found")
    void testDeleteCustomerCustomerNotFound(){

        when(customerRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(CustomerNotFound.class, () -> {
            mainClass.getCustomer(uuid);
        });

    }

    @Test
    @DisplayName("get customer by name")
    void testGetCustomerByName(){

        when(customerRepository.findByName(any())).thenReturn(Collections.singletonList(customer));

        Customer customer = mainClass.getCustomerByName(customerRequest.getName());

        assertNotNull(customer);
        assertNotNull(customer.getId());
        assertEquals(uuid.toString(), customer.getId().toString());
    }

    @Test
    @DisplayName("get customer by name - customer not found")
    void testGetCustomerByNameCustomerNotFound(){

        when(customerRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(CustomerNotFound.class, () -> {
            mainClass.getCustomer(uuid);
        });

    }

    @Test
    @DisplayName("get customer by email")
    void testGetCustomerByEmail(){
        when(customerRepository.findByEmail(any())).thenReturn(Collections.singletonList(customer));

        Customer customer = mainClass.getCustomerByEmail(customerRequest.getEmail());

        assertNotNull(customer);
        assertNotNull(customer.getId());
        assertEquals(uuid.toString(), customer.getId().toString());
    }

    @Test
    @DisplayName("get customer by email - customer not found")
    void testGetCustomerByEmailCustomerNotFound(){

        when(customerRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(CustomerNotFound.class, () -> {
            mainClass.getCustomer(uuid);
        });

    }

    private Customer customer(){
        return new Customer(uuid, customerRequest.getName(), customerRequest.getEmail(), customerRequest.getAnnualSpend(), customerRequest.getLastPurchaseDate());
    }

    private CustomerRequest createReq(){
        CustomerRequest request = new CustomerRequest();
        request.setAnnualSpend(100d);
        request.setEmail("test@test.com");
        request.setName("Test");
        request.setLastPurchaseDate(new Date());
        return request;
    }

    private CustomerRequest customerRequest() {
        CustomerRequest cusReq = new CustomerRequest();
        cusReq.setAnnualSpend(100d);
        cusReq.setEmail("test@test.com");
        cusReq.setName("Test");
        cusReq.setLastPurchaseDate(new Date());
        return cusReq;
    }
}
