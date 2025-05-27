package com.tcs.assignment.customer.data;

import com.tcs.assignment.customer.entity.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

/**
 *
 * Repository
 *
 */
public interface CustomerRepository extends CrudRepository<Customer, UUID> {

    List<Customer> findByName(String name);
    List<Customer> findByEmail(String email);
}

