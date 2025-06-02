package com.tcs.assignment.customer.service;

import com.tcs.assignment.customer.data.CustomerRepository;
import com.tcs.assignment.customer.entity.Customer;
import com.tcs.assignment.customer.exception.CustomerNotFound;
import com.tcs.assignment.customer.exception.ErrorCode;
import com.tcs.assignment.generate.model.CustomerRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    private CustomerRepository customerRepository;

    public Customer getCustomer(UUID id){
        logger.info("get customer by id in service class - {}", id.toString());
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if(customerOptional.isEmpty()){
            throw new CustomerNotFound(ErrorCode.CUSTOMER_NOT_FOUND);
        }
        return customerOptional.get();
    }

    public Customer createCustomer(CustomerRequest customerRequest){
        logger.info("create customer service class");
        // convert model to entity and save
        return customerRepository.save(customer(null, customerRequest));

    }

    public Customer updateCustomer(String id, CustomerRequest customerRequest){
        logger.info("update customer started in service class - {}", id.toString());
        Optional<Customer> custOpt = customerRepository.findById(UUID.fromString(id));
        if(custOpt.isEmpty()){
            throw new CustomerNotFound(ErrorCode.CUSTOMER_NOT_FOUND);
        }
        // convert model to entity and update
        return customerRepository.save(customer(UUID.fromString(id), customerRequest));
    }

    public void deleteCustomer(UUID id){
        logger.info("delete customer started in service class - {}", id.toString());
        Optional<Customer> custOpt = customerRepository.findById(id);
        if(custOpt.isEmpty()){
            throw new CustomerNotFound(ErrorCode.CUSTOMER_NOT_FOUND);
        }
        customerRepository.deleteById(id);
    }

    public Customer getCustomerByName(String name){
        logger.info("get customer by name {}", name);
        List<Customer> customers = customerRepository.findByName(name);
        if(customers == null || customers.isEmpty()){
            throw new CustomerNotFound(ErrorCode.CUSTOMER_NOT_FOUND);
        }
        return customers.get(0);
    }


    public Customer getCustomerByEmail(String email){
        logger.info("get customer by email {}", email);
        List<Customer> customers = customerRepository.findByEmail(email);
        if(customers == null || customers.isEmpty()){
            throw new CustomerNotFound(ErrorCode.CUSTOMER_NOT_FOUND);
        }
        return customers.get(0);
    }

    private Customer customer(UUID id, CustomerRequest customerRequest){
        return new Customer(id == null ? null : id, customerRequest.getName(), customerRequest.getEmail(), customerRequest.getAnnualSpend(), customerRequest.getLastPurchaseDate());
    }
}
