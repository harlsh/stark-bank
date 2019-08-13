package com.dbs.starkbank.repository;

import com.dbs.starkbank.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchRepository extends JpaRepository<Branch, Long> {
}
