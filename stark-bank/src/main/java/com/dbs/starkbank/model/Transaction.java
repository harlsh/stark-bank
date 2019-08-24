package com.dbs.starkbank.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

 private double accountBalance;
    private double transactionAmount;
    private LocalDate transactionTime;
    @ManyToOne
    @JoinColumn(name = "credited_account", nullable = false)
    private Account creditedAccount;

    @ManyToOne
    @JoinColumn(name = "debited_account", nullable = false)
    private Account debitedAccount;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction)) return false;
        Transaction that = (Transaction) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
