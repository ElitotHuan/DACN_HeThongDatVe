package com.example.services;

import com.example.dto.BranchDTO;
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

    public Boolean addBranch(BranchDTO branchDTO) {
        Branch newBranch = new Branch();
        newBranch.setName(branchDTO.getName());
        newBranch.setDiaChi(branchDTO.getAddress());
        newBranch.setPhoneNo(branchDTO.getPhoneNo());
        branchRepository.save(newBranch);
        return true;
    }

    public Boolean updateBranch(int id, BranchDTO branchDTO) {
        Branch newBranch = branchRepository.getById(id);
        newBranch.setName(branchDTO.getName());
        newBranch.setDiaChi(branchDTO.getAddress());
        newBranch.setPhoneNo(branchDTO.getPhoneNo());
        branchRepository.save(newBranch);
        return true;
    }

    public Boolean deleteBranch(int id) {
        branchRepository.deleteById(id);
        return true;
    }
}
