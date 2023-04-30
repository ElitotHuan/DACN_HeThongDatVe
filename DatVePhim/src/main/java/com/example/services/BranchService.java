package com.example.services;

import com.example.models.Branch;
import com.example.repositories.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchService {

    @Autowired
    private BranchRepository branchRepository;

    public List<Branch> getAll() {
        return branchRepository.findAll();
    }
}
