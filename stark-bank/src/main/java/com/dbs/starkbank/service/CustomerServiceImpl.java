package com.dbs.starkbank.service;

import com.dbs.starkbank.model.BankUser;
import com.dbs.starkbank.model.Customer;
import com.dbs.starkbank.repository.BankUserRepository;
import com.dbs.starkbank.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CustomerServiceImpl implements CustomerService {

    CustomerRepository customerRepository;
    BankUserRepository bankUserRepository;
    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, BankUserRepository bankUserRepository){
        this.bankUserRepository = bankUserRepository;
        this.customerRepository = customerRepository;
    }
    @Override
    public Customer saveCustomer(Customer customer) {
        //Do not save to the DB, instead pass this to the bankuser.
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

    @Override
    public void assignBankUser(Customer customer) {
        BankUser bankUser = customer.getBranch().getRandomBankUser();
        bankUser.addCustomer(customer);
        customerRepository.save(customer);
    }

}
