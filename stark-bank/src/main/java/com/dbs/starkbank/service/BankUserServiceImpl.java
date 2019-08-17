package com.dbs.starkbank.service;

import com.dbs.starkbank.model.BankUser;
import com.dbs.starkbank.model.Customer;
import com.dbs.starkbank.repository.BankUserRepository;
import com.dbs.starkbank.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankUserServiceImpl implements BankUserService {
    BankUserRepository bankUserRepository;
    CustomerRepository customerRepository;

    @Autowired
    public BankUserServiceImpl(BankUserRepository bankUserRepository, CustomerRepository customerRepository){
        this.bankUserRepository = bankUserRepository;
        this.customerRepository = customerRepository;
    }
    @Override
    public BankUser saveBankUser(BankUser bankUser) {
        return bankUserRepository.save(bankUser);
    }

    @Override
    public List<BankUser> listAll() {
        return bankUserRepository.findAll();
    }

    @Override
    public BankUser findById(long id) {
        Optional<BankUser> bankUser = bankUserRepository.findById(id);
        if(bankUser.isPresent()) return bankUser.get();
        return null;
    }

    @Override
    public void deleteBankUser(long id) {
        bankUserRepository.deleteById(id);
    }

    @Override
    public Customer saveCustomer(long id) {
        return null;
    }

    @Override
    public void createCustomerLogin(long id, long bid, long cid){
        Customer customer = this.customerRepository.findById(cid).get();
        BankUser bankUser = customer.getBankUser();
        if(bankUser.getCustomers().contains(customer)){
            bankUser.createCustomerLogin(customer);
        }
        this.customerRepository.save(customer);
    }
}
