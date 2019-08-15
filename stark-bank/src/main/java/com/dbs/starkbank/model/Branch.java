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
    private String ifsc ;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @OneToMany(mappedBy="branch", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Account> accounts = new HashSet<>();

    @OneToMany(mappedBy="branch", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<BankUser> bankUsers = new HashSet<>();

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "branch", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Customer> customers = new HashSet<>();

    @JsonIgnore
    public BankUser getRandomBankUser(){
        List<BankUser> bankUsers = new ArrayList<>(this.bankUsers);
        Collections.shuffle(bankUsers);
        return bankUsers.get(0);
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
