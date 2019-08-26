package com.dbs.starkbank.controller;


import com.dbs.starkbank.model.BankUser;
import com.dbs.starkbank.model.Branch;
import com.dbs.starkbank.model.Customer;
import com.dbs.starkbank.service.BankUserService;
import com.dbs.starkbank.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("api/v1/branches")
public class BranchRESTController {

    @Autowired
    private BranchService branchService;

    @Autowired
    private BankUserService bankUserService;

    @GetMapping("/")
    public List<Branch> listAllBranches(){ return this.branchService.listAll(); }

    @PostMapping("/")
    public void saveBranch(@RequestBody Branch branch){
        System.out.println(branch);
        this.branchService.saveBranch(branch);
    }
    @GetMapping("/{id}")
    public Branch getBranch(@PathVariable long id){
        return this.branchService.findById(id);
    }

    @GetMapping("/{id}/bankusers")
    public List<BankUser> listAllBankUsers(@PathVariable long id){
        return this.bankUserService.listAll();
    }

    @PostMapping("/{id}/bankusers")
    public void saveBankUser(@RequestBody BankUser bankUser,@PathVariable long id){
        System.out.println("Got a post!");
        this.branchService.saveBankUser(id, bankUser);
    }
    @GetMapping("/{id}/bankusers/{bid}")
    public BankUser GetBankUser(@PathVariable long id, @PathVariable long bid){
        return this.bankUserService.findById(bid);
    }

    @PostMapping("/{id}/bankusers/{bid}/createLogin/{cid}")
    public void createCustomerLogin(@PathVariable long id, @PathVariable long cid, @PathVariable long bid){
        this.bankUserService.createCustomerLogin(id, bid, cid);
    }
    @GetMapping("/{id}/customers")
    public Set<Customer> listAllCustomers(@PathVariable long id){ return this.branchService.listAllCustomers(id);}

    @PostMapping("/{id}/customers")
    public void saveCustomer(@RequestBody Customer customer, @PathVariable long id){
        System.out.println("Got a customer creation Post!");
        this.branchService.saveCustomer(customer,id);
    }
}
