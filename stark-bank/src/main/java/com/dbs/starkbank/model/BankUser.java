package com.dbs.starkbank.model;

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

    @ManyToOne
    @JoinColumn(name = "branch_id", nullable = false)
    private Branch branch;

    public void addCustomer(Customer customer){
        customers.add(customer);
        customer.setBankUser(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BankUser)) return false;
        BankUser bankUser = (BankUser) o;
        return id == bankUser.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
