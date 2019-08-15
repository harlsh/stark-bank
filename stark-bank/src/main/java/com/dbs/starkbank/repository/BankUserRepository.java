package com.dbs.starkbank.repository;

import com.dbs.starkbank.model.BankUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankUserRepository extends JpaRepository<BankUser, Long> {
}
