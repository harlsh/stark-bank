package com.dbs.starkbank.service;

import com.dbs.starkbank.model.Customer;
import com.dbs.starkbank.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    CustomerRepository customerRepository;
    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }
    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> listAll() {
        return this.customerRepository.findAll();
    }

    @Override
    public Customer findById(long id) {
        return null;
    }

    @Override
    public void deleteCustomer(long id) {

    }
}
