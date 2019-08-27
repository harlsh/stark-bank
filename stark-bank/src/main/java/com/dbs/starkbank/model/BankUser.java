package com.dbs.starkbank.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
public class BankUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String userName;
    private String password;
    @OneToMany(mappedBy = "bankUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Customer> customers = new HashSet<>();

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "branch_id", nullable = false)
    private Branch branch;

    public void addCustomer(Customer customer){
        this.customers.add(customer);
        customer.setBankUser(this);
    }

    public void createCustomerLogin(Customer customer){
        customer.setLogin(true);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        BankUser bankUser = (BankUser) o;
        return bankUser.getUserName().equals(this.getUserName()) && bankUser.getPassword().equals(this.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
