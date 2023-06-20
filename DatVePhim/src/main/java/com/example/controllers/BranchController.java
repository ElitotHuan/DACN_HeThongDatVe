package com.example.controllers;

import com.example.dto.BranchDTO;
import com.example.dto.MovieDTO;
import com.example.services.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class BranchController {

    @Autowired
    BranchService service;

    @PostMapping(value = "/api/addBranch")
    public String addMovie(@RequestBody BranchDTO branchDTO) {
        System.out.println(branchDTO.toString());
        Boolean ad = service.addBranch(branchDTO);
        return "success";
    }

    @PutMapping(value = "/api/updateBranch/{id}")
    public String updateMovie(@PathVariable("id") int id, @RequestBody BranchDTO branchDTO) {
        Boolean up = service.updateBranch(id, branchDTO);
        return "success";
    }

    @RequestMapping(value = "/api/deleteBranch/{id}", method = RequestMethod.GET)
    public ModelAndView deleteMovie(@PathVariable("id") int id) {
        Boolean del = service.deleteBranch(id);
        return new ModelAndView("redirect:/api/manage_branch");
    }

}