package com.dbs.starkbank.service;

import com.dbs.starkbank.model.Branch;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchService {
    Branch saveBranch(Branch branch);
    List<Branch> listAll();
    Branch findById(long id);
    void deleteBranch(long id);

}
