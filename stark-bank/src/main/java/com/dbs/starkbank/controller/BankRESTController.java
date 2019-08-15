package com.dbs.starkbank.controller;

import com.dbs.starkbank.model.Branch;
import com.dbs.starkbank.model.Customer;
import com.dbs.starkbank.service.BranchService;
import com.dbs.starkbank.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class BankRESTController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private BranchService branchService;

    @GetMapping( "/customers")
    public List<Customer> listAllCustomers(){
        return this.customerService.listAll();
    }

    @PostMapping("/customers")
    public void saveCustomer(@RequestBody Customer customer){
        System.out.println("Got a post!");
        this.customerService.saveCustomer(customer);
    }

    @GetMapping("/branches")
    public List<Branch> listAllBranches(){ return this.branchService.listAll(); }

    @PostMapping("/branches")
    public void saveBranch(@RequestBody Branch branch){
        System.out.println("Got a post!");
        System.out.println(branch);
        this.branchService.saveBranch(branch);
    }
}
