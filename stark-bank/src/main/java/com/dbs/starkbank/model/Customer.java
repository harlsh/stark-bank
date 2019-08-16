package com.dbs.starkbank.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String gender;
    private String dateOfBirth;
    private String nationalId;
    private String userId;
    private String password;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    private Branch branch;

    @JsonIgnore
    @ToString.Exclude
    @ManyToOne
    private BankUser bankUser;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @OneToMany(mappedBy="customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Account> accounts = new HashSet<>();

    public void addAccount(Account account){
        this.accounts.add(account);
        account.setCustomer(this);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return id == customer.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
