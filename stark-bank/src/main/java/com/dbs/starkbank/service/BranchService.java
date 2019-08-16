package com.dbs.starkbank.service;

import com.dbs.starkbank.model.BankUser;
import com.dbs.starkbank.model.Branch;
import com.dbs.starkbank.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface BranchService {
    Branch saveBranch(Branch branch);
    List<Branch> listAll();
    Branch findById(long id);
    void deleteBranch(long id);
    Set<BankUser> listAllBankUsers(long id);
    Branch saveBankUser(long id, BankUser bankUser);
    BankUser getBankUser(long id, long bid);
    Set<Customer> listAllCustomers(long id);
    Customer saveCustomer(Customer customer, long id);
    public void assignBankUser(Customer customer);
}
