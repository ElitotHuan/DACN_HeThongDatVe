package com.example.controllers;

import com.example.dto.EmployeeDTO;
import com.example.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @PostMapping(value = "/api/addEmployee")
    public String addEmployee(@RequestBody EmployeeDTO empDTO) {
        if (empDTO == null) {
            return "error: employee data is null";
        }
        System.out.println(empDTO.toString());
        Boolean ad = service.addEmployee(empDTO);
        return "success";
    }


    @PutMapping(value = "/api/updateEmployee/{id}")
    public String updateEmployee(@PathVariable("id") int id, @RequestBody EmployeeDTO empDTO) {
        if (empDTO == null) {
            return "error: employee data is null";
        }
        Boolean up = service.updateEmployee(empDTO, id);
        return "success";
    }

    @RequestMapping(value = "/api/deleteEmployee/{id}", method = RequestMethod.GET)
    public ModelAndView deleteEmployee(@PathVariable("id") int id) {
        Boolean del = service.deleteEmployee(id);
        return new ModelAndView("redirect:/api/manage_employee");
    }



}