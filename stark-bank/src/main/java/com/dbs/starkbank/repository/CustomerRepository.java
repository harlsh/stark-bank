package com.dbs.starkbank.repository;

import com.dbs.starkbank.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Set<Customer> findByUserId(String userId);
}
