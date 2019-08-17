package com.dbs.starkbank.service;

import com.dbs.starkbank.model.BankUser;
import com.dbs.starkbank.model.Customer;

import java.util.List;

public interface BankUserService {
    BankUser saveBankUser(BankUser bankUser);
    List<BankUser> listAll();
    BankUser findById(long id);
    void deleteBankUser(long id);
    Customer saveCustomer(long id);
    void createCustomerLogin(long id, long bid, long cid);
}
