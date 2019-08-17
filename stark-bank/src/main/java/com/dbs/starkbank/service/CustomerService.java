package com.dbs.starkbank.service;

import com.dbs.starkbank.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerService {
    Customer saveCustomer(Customer customer);
    List<Customer> listAll();
    Customer findById(long id);
    void deleteCustomer(long id);
    Acc
}
