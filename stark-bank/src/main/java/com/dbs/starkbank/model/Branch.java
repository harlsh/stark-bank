package com.dbs.starkbank.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column( unique = true)
    private String branchName;
    private String ifsc ;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @OneToMany(mappedBy="branch", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Account> accounts = new HashSet<>();

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy="branch", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<BankUser> bankUsers = new HashSet<>();

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Customer> customers = new HashSet<>();

    public void addBankUser(BankUser bankUser){
        this.bankUsers.add(bankUser);
        bankUser.setBranch(this);
    }

    public void addCustomer(Customer customer){
        this.customers.add(customer);
        customer.setBranch(this);
    }

    @JsonIgnore
    public BankUser getRandomBankUser(){
        System.out.println(this.bankUsers);
        List<BankUser> bankUsers = new ArrayList<>(this.bankUsers);
        System.out.println(bankUsers);
        Collections.shuffle(bankUsers);
        return bankUsers.size() > 0 ? bankUsers.get(0): null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Branch)) return false;
        Branch branch = (Branch) o;
        return id == branch.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
