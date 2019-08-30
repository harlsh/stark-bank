package com.dbs.starkbank.controller;

import com.dbs.starkbank.model.Account;
import com.dbs.starkbank.model.Customer;
import com.dbs.starkbank.model.Transaction;
import com.dbs.starkbank.service.CustomerService;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("api/v1/customers")
public class CustomerRESTController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/")
    public List<Customer> listAllCustomers(){
        return this.customerService.listAll();
    }

    @PostMapping("/login")
    public Customer customerLogin(@RequestBody ObjectNode json){
        System.out.println("Loggin in");
        System.out.println(json);
        System.out.println("the user id is " + json.get("userId").asText());
        return this.customerService.loginCustomer(json.get("userId").asText(), json.get("password").asText());
    }
    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable long id){
        System.out.println("Deleting Customer");
        this.customerService.deleteCustomer(id);
    }
    @GetMapping("/{id}")
    public Customer getCustomer(@PathVariable long id){
        return this.customerService.findById(id);
    }
    @GetMapping("/{id}/accounts")
    public Set<Account> getCustomerAccount(@PathVariable long id){
        return this.customerService.findById(id).getAccounts();
    }
    @PostMapping("/{id}/accounts")
    public void setCustomerAccount(@PathVariable long id, @RequestBody Account account){
        Customer customer = this.customerService.findById(id);
        customer.addAccount(account);
        this.customerService.saveCustomer(customer);
    }
    @GetMapping("/{id}/accounts/{aid}/transactions")
    public Set<Transaction> getCustomerTransactions(@PathVariable long id, @PathVariable long aid){

        return  this.customerService.getTransctions(id,aid);
    }

    @GetMapping("/{id}/accounts/{aid}/account")
    public Account getCustomerAccountByAccountNo(@PathVariable long id, @PathVariable long aid){
      return this.customerService.findByAccountId(aid);
    }
    @PostMapping("/{id}/accounts/{aid}/transactions")
    public void getCustomerTransactions(@PathVariable long id, @PathVariable long aid,@RequestBody Transaction transaction){
        this.customerService.saveTransaction(id,aid,transaction);
    }
    @PutMapping("/{id}")
    public Customer editCustomer(@PathVariable long id, @RequestBody Customer customer)
    {
        return this.customerService.editCustomer(id,customer);
    }

    @PostMapping("/{id}/accounts/{aid}/transactions/withdraw")
    public void withdraw(@PathVariable long id, @PathVariable long aid,@RequestBody Transaction transaction){
        this.customerService.withdraw(id,aid,transaction);
    }
    @PostMapping("/{id}/accounts/{aid}/transactions/deposit")
    public void deposit(@PathVariable long id, @PathVariable long aid,@RequestBody Transaction transaction){
        this.customerService.deposit(id,aid,transaction);
    }
    @PostMapping("/{id}/accounts/{aid}/transactions/transfer")
    public void transfer(@PathVariable long id, @PathVariable long aid, @RequestBody Transaction transaction){
      System.out.println("TRANSFERRRRR");
      this.customerService.transfer(id,aid, transaction);
    }
    @PostMapping("/")
    public void findCustomer(@RequestBody String userId){
        System.out.println(userId);
        Set<Customer> customers = this.customerService.findByUserId(userId);
        System.out.println(customers);
    }

}
