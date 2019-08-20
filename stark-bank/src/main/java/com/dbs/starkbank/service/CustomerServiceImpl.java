package com.dbs.starkbank.service;

import com.dbs.starkbank.model.*;
import com.dbs.starkbank.repository.*;
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
    TransactionRepository transactionRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, BranchRepository branchRepository, AccountRepository accountRepository){
        this.branchRepository = branchRepository;
        this.accountRepository=accountRepository;
        this.customerRepository = customerRepository;
        this.transactionRepository= transactionRepository;
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



    @Override
    public Customer editCustomer(long id, Customer customer) {
        //System.out.println(customer);

        Customer fetchedCustomer=this.customerRepository.findById(id).get();
        if(fetchedCustomer != null){
            if(customer.getAccounts() != null)
                fetchedCustomer.setAccounts(customer.getAccounts());
            if(customer.getFirstName() != null)
                fetchedCustomer.setFirstName(customer.getFirstName());
            if(customer.getLastName()!=null)
                fetchedCustomer.setLastName((customer.getLastName()));
            if(customer.getPhoneNumber()!=null)
                fetchedCustomer.setPhoneNumber(customer.getPhoneNumber());
            if(customer.getGender()!=null)
                fetchedCustomer.setGender(customer.getGender());
            if(customer.getDateOfBirth()!=null)
                fetchedCustomer.setDateOfBirth(customer.getDateOfBirth());
            if(customer.getNationalId()!=null)
                fetchedCustomer.setNationalId(customer.getNationalId());
            if(customer.getUserId()!=null)
                fetchedCustomer.setUserId(customer.getUserId());
            if(customer.getPassword()!=null)
                fetchedCustomer.setPassword(customer.getPassword());
            if(customer.getAddress()!=null)
                fetchedCustomer.setAddress(customer.getAddress());
        }




        return this.customerRepository.save(fetchedCustomer);
    }

    @Override
    public Transaction withdraw(long id, long aid, Transaction transaction) {
        Customer customer= this.customerRepository.findById(id).get();
        Account account= this.accountRepository.findById(aid).get();
        account.setBalance(account.getBalance()-transaction.getTransactionAmount());
        System.out.println("**************" + account.getBalance());
        //Account account1= transaction.getDebitedAccount();
        account.addTransactions(transaction);
        this.accountRepository.save(account);
        return transaction;
    }


}
