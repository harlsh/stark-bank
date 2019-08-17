package com.dbs.starkbank.controller;

import com.dbs.starkbank.model.*;
import com.dbs.starkbank.service.BankUserService;
import com.dbs.starkbank.service.BranchService;
import com.dbs.starkbank.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/v1")
public class BankRESTController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private BranchService branchService;

    @Autowired
    private BankUserService bankUserService;

    @GetMapping( "/customers")
    public List<Customer> listAllCustomers(){
        return this.customerService.listAll();
    }

    @GetMapping("/customers/{id}")
    public Customer getCustomer(@PathVariable long id){
        return this.customerService.findById(id);
    }
    @GetMapping("/customers/{id}/accounts")
    public Set<Account> getCustomerAccount(@PathVariable long id){
        return this.customerService.findById(id).getAccounts();
    }
    @PostMapping("/customers/{id}/accounts")
    public void setCustomerAccount(@PathVariable long id, @RequestBody Account account){
        Customer customer = this.customerService.findById(id);
        customer.addAccount(account);
        this.customerService.saveCustomer(customer);
    }
    @GetMapping("/customers/{id}/accounts/{aid}/transactions")
    public Set<Transaction> getCustomerTransactions(@PathVariable long id, @PathVariable long aid){
       return  null;
    }
    //@PostMapping("/customers/{id}/accounts")


    @GetMapping("/branches")
    public List<Branch> listAllBranches(){ return this.branchService.listAll(); }

    @PostMapping("/branches")
    public void saveBranch(@RequestBody Branch branch){
        System.out.println("Got a post!");
        System.out.println(branch);
        this.branchService.saveBranch(branch);
    }
    @GetMapping("/branches/{id}")
    public Branch getBranch(@PathVariable long id){
        return this.branchService.findById(id);
    }

    @GetMapping("/branches/{id}/bankusers")
    public List<BankUser> listAllBankUsers(@PathVariable long id){
        return this.bankUserService.listAll();
    }

    @PostMapping("/branches/{id}/bankusers")
    public void saveBankUser(@RequestBody BankUser bankUser,@PathVariable long id){
        System.out.println("Got a post!");
        this.branchService.saveBankUser(id, bankUser);
    }
    @GetMapping("/branches/{id}/bankusers/{bid}")
    public BankUser GetBankUser(@PathVariable long id, @PathVariable long bid){
        return this.bankUserService.findById(bid);
    }

    @PostMapping("/branches/{id}/bankusers/{bid}/createLogin/{cid}")
    public void createCustomerLogin(@PathVariable long id, @PathVariable long cid, @PathVariable long bid){
        this.bankUserService.createCustomerLogin(id, bid, cid);
    }
    @GetMapping("/branches/{id}/customers")
    public Set<Customer> listAllCustomers(@PathVariable long id){ return this.branchService.listAllCustomers(id);}

    @PostMapping("/branches/{id}/customers")
    public void saveCustomer(@RequestBody Customer customer, @PathVariable long id){
        this.branchService.saveCustomer(customer,id);
    }

}
