package com.example.services;

import com.example.dto.BranchDTO;
import com.example.models.Branch;
import com.example.repositories.BranchRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BranchServiceTest {

    @Mock
    private BranchRepository branchRepository;

    @InjectMocks
    private BranchService branchService;

    @Test
    void testGetAll() {
        List<Branch> branches = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Branch branch = new Branch();
            branch.setId(i + 1);
            branch.setName("Branch" + (i + 1));
            branch.setDiaChi("Address" + (i + 1));
            branch.setPhoneNo("PhoneNo" + (i + 1));
            branches.add(branch);
        }

        when(branchRepository.findAll()).thenReturn(branches);

        List<Branch> result = branchService.getAll();
        assertEquals(branches.size(), result.size());
    }

    @Test
    void testAddBranch() {
        BranchDTO branchDTO = new BranchDTO();
        branchDTO.setName("New Branch");
        branchDTO.setAddress("New Address");
        branchDTO.setPhoneNo("New PhoneNo");

        boolean result = branchService.addBranch(branchDTO);
        assertTrue(result);

        verify(branchRepository).save(any(Branch.class));
    }

    @Test
    void testUpdateBranch() {
        Branch branch = new Branch();
        branch.setId(1);
        branch.setName("Old Branch");
        branch.setDiaChi("Old Address");
        branch.setPhoneNo("Old PhoneNo");

        when(branchRepository.getById(1)).thenReturn(branch);

        BranchDTO branchDTO = new BranchDTO();
        branchDTO.setName("Updated Branch");
        branchDTO.setAddress("Updated Address");
        branchDTO.setPhoneNo("Updated PhoneNo");

        boolean result = branchService.updateBranch(1, branchDTO);
        assertTrue(result);

        verify(branchRepository).save(any(Branch.class));
    }

    @Test
    void testDeleteBranch() {
        boolean result = branchService.deleteBranch(1);
        assertTrue(result);

        verify(branchRepository).deleteById(1);
    }
}