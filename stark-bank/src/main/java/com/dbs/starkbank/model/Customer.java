package com.dbs.starkbank.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
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

    @ManyToOne(cascade = CascadeType.ALL)
    private Branch branch;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @OneToMany(mappedBy="customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Account> accounts = new HashSet<>();

    @ManyToOne
    private BankUser bankUser;
}
