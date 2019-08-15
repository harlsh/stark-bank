package com.dbs.starkbank.service;

import com.dbs.starkbank.model.Branch;
import com.dbs.starkbank.repository.BranchRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BranchServiceImpl implements BranchService {
    private BranchRepository branchRepository;

    public BranchServiceImpl(BranchRepository branchRepository){
        this.branchRepository = branchRepository;
    }

    @Override
    public Branch saveBranch(Branch branch) {
        return branchRepository.save(branch);
    }

    @Override
    public List<Branch> listAll() {
        return branchRepository.findAll();
    }

    @Override
    public Branch findById(long id) {
        Optional<Branch> branch = branchRepository.findById(id);
        if(branch.isPresent()) return branch.get();
        return null;
    }

    @Override
    public void deleteBranch(long id) {
        branchRepository.deleteById(id);
    }


}
