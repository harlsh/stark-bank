package com.dbs.starkbank.controller;

import com.dbs.starkbank.model.Customer;
import com.dbs.starkbank.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class BankRESTController {

    @Autowired
    private CustomerService customerService;

    @GetMapping( value = "/customers")
    public List<Customer> listAll(){
        return this.customerService.listAll();
    }

    @PostMapping("/customers")
    public void saveCustomer(@RequestBody Customer customer){
        System.out.println("Got a post!");
        this.customerService.saveCustomer(customer);
    }
}
