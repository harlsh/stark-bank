package com.dbs.starkbank.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Bank {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String bankName;

    @OneToMany(mappedBy="bank", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Set<Branch> branches = new HashSet<>();
}
