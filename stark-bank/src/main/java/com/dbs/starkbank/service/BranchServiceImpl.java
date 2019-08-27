package com.dbs.starkbank.service;

import com.dbs.starkbank.model.BankUser;
import com.dbs.starkbank.model.Branch;
import com.dbs.starkbank.model.Customer;
import com.dbs.starkbank.repository.BankUserRepository;
import com.dbs.starkbank.repository.BranchRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class BranchServiceImpl implements BranchService {
    private BranchRepository branchRepository;
    private BankUserRepository bankUserRepository;
    public BranchServiceImpl(BranchRepository branchRepository, BankUserRepository bankUserRepository){
        this.branchRepository = branchRepository;
        this.bankUserRepository = bankUserRepository;
    }

    @Override
    public Branch saveBranch(Branch branch) {
        return branchRepository.save(branch);
    }

    @Override
    public List<Branch> listAll() {
        return branchRepository.findAll();
    }

    @Override
    public Branch findById(long id) {
        Optional<Branch> branch = branchRepository.findById(id);
        if(branch.isPresent()) return branch.get();
        return null;
    }

    @Override
    public void deleteBranch(long id) {
        branchRepository.deleteById(id);
    }

    @Override
    public Set<BankUser> listAllBankUsers(long id) {
        return this.branchRepository.findById(id).get().getBankUsers();
    }

    @Override
    public Branch saveBankUser(long id, BankUser bankUser) {
        Branch branch = this.branchRepository.findById(id).get();
        branch.addBankUser(bankUser);
        return this.branchRepository.save(branch);
    }

    @Override
    public BankUser getBankUser(long id, long bid) {
        return null;
    }

    @Override
    public Set<Customer> listAllCustomers(long id) {
        return this.branchRepository.findById(id).get().getCustomers();
    }

    @Transactional
    @Override
    public Customer saveCustomer(Customer customer, long id) {
        System.out.println("Saving customer: " + customer);
        Branch branch = this.branchRepository.findById(id).get();
        branch.addCustomer(customer);
        assignBankUser(customer);
        return customer;
    }

    @Override
    public void assignBankUser(Customer customer) {
        System.out.println("assigning the customer a bankuser");
        System.out.println("branch = " + customer.getBranch());
        BankUser bankUser = customer.getBranch().getRandomBankUser();
        System.out.println("The bankuser is " + bankUser);
        bankUser.addCustomer(customer);
        this.branchRepository.save(customer.getBranch());
    }

}
