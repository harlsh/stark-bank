package com.dbs.starkbank.service;

import com.dbs.starkbank.model.*;
import com.dbs.starkbank.repository.AccountRepository;
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
    AccountRepository accountRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, BranchRepository branchRepository, AccountRepository accountRepository){
        this.branchRepository = branchRepository;
        this.accountRepository=accountRepository;
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

    @Override
    public Set<Transaction> getTransctions(long id, long aid) {
        Customer customer= this.customerRepository.findById(id).get();
        Account account= this.accountRepository.findById(aid).get();
        if (customer.getAccounts().contains(account))
        {
            return account.getTransactions();
        }
        return null;

    }

    @Override
    public Transaction saveTransaction(long id, long aid, Transaction transaction) {
        Customer customer= this.customerRepository.findById(id).get();
        Account account= this.accountRepository.findById(aid).get();
        account.addTransactions(transaction);
        this.accountRepository.save(account);
       return transaction;
    }


}
