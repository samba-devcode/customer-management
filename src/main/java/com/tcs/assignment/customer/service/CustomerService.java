package com.tcs.assignment.customer.service;

import com.tcs.assignment.customer.data.CustomerRepository;
import com.tcs.assignment.customer.entity.Customer;
import com.tcs.assignment.generate.model.CustomerRequest;
import com.tcs.assignment.generate.model.CustomerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer getCustomer(UUID id){
        Optional<Customer> customerOptional = customerRepository.findById(id);
        return customerOptional.get();
    }

    public Customer createCustomer(CustomerRequest customerRequest){
        // convert model to entity and save
        return customerRepository.save(customer(null, customerRequest));

    }

    public Customer updateCustomer(String id, CustomerRequest customerRequest){
        // convert model to entity and update
        return customerRepository.save(customer(UUID.fromString(id), customerRequest));
    }

    public void deleteCustomer(UUID id){
        customerRepository.deleteById(id);
    }

    public Customer getCustomerByName(String name){
        List<Customer> customers = customerRepository.findByName(name);
        return customers.get(0);
    }


    public Customer getCustomerByEmail(String email){
        List<Customer> customers = customerRepository.findByEmail(email);
        return customers.get(0);
    }

    private Customer customer(UUID id, CustomerRequest customerRequest){
        return new Customer(id == null ? null : id, customerRequest.getName(), customerRequest.getEmail(), customerRequest.getAnnualSpend(), customerRequest.getLastPurchaseDate());
    }
}
