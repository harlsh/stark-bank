package com.dbs.starkbank.repository;

import com.dbs.starkbank.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface AccountRepository extends JpaRepository<Account, Long> {
  Set<Account> findByAccountNumber(long accountNumber);
}
