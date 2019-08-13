package com.dbs.starkbank.repository;

import com.dbs.starkbank.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
