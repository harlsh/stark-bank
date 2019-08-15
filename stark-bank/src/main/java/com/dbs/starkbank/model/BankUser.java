package com.dbs.starkbank.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class BankUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String userName;
    private String password;
    @OneToMany(mappedBy = "bankUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Customer> customers = new HashSet<>();


}
