package com.dbs.starkbank.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class BankUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private Set<Customer> customers = new HashSet<>();

    //to do
}
