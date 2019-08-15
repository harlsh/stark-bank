package com.dbs.starkbank.service;

import com.dbs.starkbank.model.BankUser;
import com.dbs.starkbank.model.Customer;
import com.dbs.starkbank.repository.BankUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankUserServiceImpl implements BankUserService {
    BankUserRepository bankUserRepository;

    @Autowired
    public BankUserServiceImpl(BankUserRepository bankUserRepository){
        this.bankUserRepository = bankUserRepository;
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
}
