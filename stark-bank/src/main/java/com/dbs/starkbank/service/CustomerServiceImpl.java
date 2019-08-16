package com.dbs.starkbank.service;

import com.dbs.starkbank.model.BankUser;
import com.dbs.starkbank.model.Branch;
import com.dbs.starkbank.model.Customer;
import com.dbs.starkbank.repository.BankUserRepository;
import com.dbs.starkbank.repository.BranchRepository;
import com.dbs.starkbank.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CustomerServiceImpl implements CustomerService {

    CustomerRepository customerRepository;
    BranchRepository branchRepository;
    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, BranchRepository branchRepository){
        this.branchRepository = branchRepository;
        this.customerRepository = customerRepository;
    }
//    @Override
//    public Customer saveCustomer(Customer customer, long id) {
//        Branch branch = branchRepository.findById(id).get();
//        customer.setBranch(branch);
//        return customerRepository.save(customer);
//    }

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
        Optional<Customer> customer = customerRepository.findById(id);
        if(customer.isPresent())
            return customer.get();
        return null;
    }

    @Override
    public void deleteCustomer(long id) {
        customerRepository.deleteById(id);
    }



}
