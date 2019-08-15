package com.dbs.starkbank.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
public class Address {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private long id;
    private String houseNumber;
    private String street;
    private String state;
    private String pin;

    @JsonIgnore
    @ToString.Exclude
    @OneToOne(mappedBy = "address")
    private Customer customer;

}
