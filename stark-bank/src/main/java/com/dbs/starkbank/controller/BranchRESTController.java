package com.dbs.starkbank.controller;


import com.dbs.starkbank.model.BankUser;
import com.dbs.starkbank.model.Branch;
import com.dbs.starkbank.model.Customer;
import com.dbs.starkbank.service.BankUserService;
import com.dbs.starkbank.service.BranchService;
import com.dbs.starkbank.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("api/v1/branches")
public class BranchRESTController {

    @Autowired
    private BranchService branchService;

    @Autowired
    private BankUserService bankUserService;

    @Autowired
    private CustomerService customerService;

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
    public Set<BankUser> listAllBankUsers(@PathVariable long id){

        return this.branchService.listAllBankUsers(id);
    }

    @PostMapping("/{id}/bankusers")
    public void saveBankUser(@RequestBody BankUser bankUser,@PathVariable long id){
        System.out.println("Got a post!");
        this.branchService.saveBankUser(id, bankUser);
    }
    @GetMapping("/{id}/bankusers/{bid}")
    public BankUser getBankUser(@PathVariable long id, @PathVariable long bid){
        return this.bankUserService.findById(bid);
    }

    @GetMapping("/{id}/bankusers/{bid}/customerlist")
    public Set<Customer> getBankUserCustomer(@PathVariable long id, @PathVariable long bid){
        return this.bankUserService.findById(bid).getCustomers().stream()
                .filter( c-> !c.isLogin())
                .collect(Collectors.toSet());
    }
    @PostMapping("/{id}/bankusers/login")
    public List<Object> bankUserLogin(@PathVariable long id, @RequestBody BankUser bankUser){
        List<Object> set = new ArrayList<>();
        List<BankUser> bankUsers = new ArrayList<BankUser>(this.branchService.listAllBankUsers(id));
        Set<Customer> customers = bankUsers.contains(bankUser)?
                this.branchService.listAllCustomers(id): null;
        set.add(customers);
        long x = bankUsers.stream().filter( b -> b.equals(bankUser)).findFirst().get().getId();
        set.add(x);
        return set;
    }


    @PostMapping("/{id}/bankusers/{bid}/createLogin/{cid}")
    public void createCustomerLogin(@PathVariable long id, @PathVariable long cid, @PathVariable long bid){
        System.out.println("Creating customer login");
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
