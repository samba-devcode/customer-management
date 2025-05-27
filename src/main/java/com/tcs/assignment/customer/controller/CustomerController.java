package com.tcs.assignment.customer.controller;


import com.tcs.assignment.customer.entity.Customer;
import com.tcs.assignment.customer.service.CustomerService;
import com.tcs.assignment.generate.api.CustomersApi;
import com.tcs.assignment.generate.model.CustomerRequest;
import com.tcs.assignment.generate.model.CustomerResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

/**
 * controller class
 */
@RestController
public class CustomerController implements CustomersApi{

    @Autowired
    private CustomerService customerService;

    @Override
    public ResponseEntity<Void> createCustomer(CustomerRequest customerRequest) {
        customerService.createCustomer(customerRequest);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<CustomerResponse> customerById(String id) {
        Customer customer = customerService.getCustomer(UUID.fromString(id));
        CustomerResponse response = getResponse(id, customer);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<CustomerResponse> customerByNameOrEmail(String name, String email) {
        Customer customer = null;
        if(StringUtils.isNoneEmpty(name) && StringUtils.isEmpty(email)){
            customer = customerService.getCustomerByName(name);
        } else if(StringUtils.isNoneEmpty(email) && StringUtils.isEmpty(name)){
            customer = customerService.getCustomerByEmail(email);
        }

        CustomerResponse response = getResponse(null, customer);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> deleteCustomer(String id) {
        customerService.deleteCustomer(UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<CustomerResponse> updateCustomer(String id, CustomerRequest customerRequest) {
        return ResponseEntity.ok(getResponse(customerService.updateCustomer(id, customerRequest)));
    }

    private CustomerResponse getResponse(String id, Customer customer) {
        double annualSpend = customer.getAnnualSpend();
        CustomerResponse response = new CustomerResponse();
        response.setId(id == null ? customer.getId().toString() : id);
        response.setEmail(customer.getEmail());
        response.setName(customer.getName());
        response.setAnnualSpend(annualSpend);
        response.setLastPurchaseDate(customer.getLastPurchaseDate());
        // decide tier
        LocalDate localDateLast12 = LocalDate.now().minusMonths(12);
        LocalDate localDateLast6 = LocalDate.now().minusMonths(6);
        CustomerResponse.TierEnum tierEnum = null;
        if(annualSpend < 1000){
            tierEnum = CustomerResponse.TierEnum.SILVER;
        } else if (annualSpend >= 1000 && annualSpend < 10000 && !customer.getLastPurchaseDate().before(Date.from(localDateLast12.atStartOfDay(ZoneId.systemDefault()).toInstant()))) {
            tierEnum = CustomerResponse.TierEnum.GOLD;
        } else if (annualSpend >= 10000  && !customer.getLastPurchaseDate().before(Date.from(localDateLast6.atStartOfDay(ZoneId.systemDefault()).toInstant()))) {
            tierEnum = CustomerResponse.TierEnum.PLATINUM;
        }
        response.setTier(tierEnum);
        return response;
    }

    private CustomerResponse getResponse(Customer customer) {
        double annualSpend = customer.getAnnualSpend();
        CustomerResponse response = new CustomerResponse();
        response.setId(customer.getId().toString());
        response.setEmail(customer.getEmail());
        response.setName(customer.getName());
        response.setAnnualSpend(annualSpend);
        response.setLastPurchaseDate(customer.getLastPurchaseDate());
        return response;
    }
}
