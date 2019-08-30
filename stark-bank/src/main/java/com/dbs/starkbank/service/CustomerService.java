package com.dbs.starkbank.service;

import com.dbs.starkbank.model.Account;
import com.dbs.starkbank.model.Customer;
import com.dbs.starkbank.model.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CustomerService {
    Customer saveCustomer(Customer customer);
    List<Customer> listAll();
    Customer findById(long id);
    void deleteCustomer(long id);
    Set<Transaction> getTransctions(long id, long aid);
    Transaction saveTransaction(long id, long aid, Transaction transaction);
    Customer editCustomer(long id, Customer customer);
    Transaction withdraw(long id, long aid, Transaction transaction);
    Set<Customer> findByUserId(String userId);
    Transaction deposit(long id, long aid, Transaction transaction);
    Customer loginCustomer(String userId, String password);
    Transaction transfer(long id, long aid, Transaction transaction);
    Account findByAccountId(long aid);
}
