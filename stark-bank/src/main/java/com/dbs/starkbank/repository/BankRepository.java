package com.dbs.starkbank.repository;

import com.dbs.starkbank.model.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<Bank, Long> {
}
